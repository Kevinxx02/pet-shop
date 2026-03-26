package com.petshop.catalog.domain.group;

import java.util.UUID;

public interface GroupRepository {
    boolean existsById(UUID groupId);
}
