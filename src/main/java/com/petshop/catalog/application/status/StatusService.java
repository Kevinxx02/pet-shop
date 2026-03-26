package com.petshop.catalog.application.status;


import com.petshop.catalog.domain.status.Status;
import com.petshop.catalog.domain.status.StatusReadRepository;
import com.petshop.catalog.domain.status.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StatusService {
    private final StatusRepository statusRepository;
    private final StatusReadRepository statusReadRepository;

    public StatusService(StatusRepository statusRepository, StatusReadRepository statusReadRepository) {
        this.statusRepository = statusRepository;
        this.statusReadRepository = statusReadRepository;
    }

    public StatusView create(String name) {
        final Status status = Status.create(name);
        this.statusRepository.save(status);

        return StatusMapper.toView(status);
    }


    public List<StatusView> viewAll() {
        return this.statusReadRepository.viewAll();
    }

    public void delete(UUID id) {
        if(!this.statusRepository.existsById(id)) {
            throw new IllegalArgumentException("El estado no existe");
        }

        this.statusRepository.deleteById(id);
    }
}
