package com.petshop.catalog.web.filter;

import java.util.UUID;

public record CreateFilterRequest(UUID parentId, UUID objectId) {}