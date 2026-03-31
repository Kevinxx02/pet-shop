package com.petshop.catalog.application.filter;


import java.util.UUID;

public record FilterView(UUID id, UUID parentId, UUID objectId) {}
