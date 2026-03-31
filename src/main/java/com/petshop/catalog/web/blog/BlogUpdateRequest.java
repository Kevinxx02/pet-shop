package com.petshop.catalog.web.blog;

import java.util.UUID;

public record BlogUpdateRequest(UUID id, String title, String date, String url, boolean isVisible) {}
