package com.petshop.catalog.application.multimedia;

import java.util.UUID;

public record MultimediaView(UUID id, String fileName, boolean isPrimary, UUID ownerId) { }
