package com.petshop.catalog.application.user;

import com.petshop.catalog.domain.shared.Email;
import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.domain.user.UserRepository;
import com.petshop.catalog.web.exception.UserAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CreateUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GetUserService getUserService;

    public CreateUserService(UserRepository userRepository,
                             PasswordEncoder passwordEncoder, GetUserService getUserService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.getUserService = getUserService;
    }

    @Transactional
    public UserView createUser(String email, String rawPassword) {
        final Optional<User> userAlreadyExist = getUserService.findByEmail(new Email(email));

        if (userAlreadyExist.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        String password = passwordEncoder.encode(rawPassword);
        User user = User.create(email, password);

        userRepository.save(user);

        return UserMapper.toView(user);
    }
}