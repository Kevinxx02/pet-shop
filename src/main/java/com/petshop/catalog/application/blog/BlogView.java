package com.petshop.catalog.application.blog;

import java.util.UUID;

public record BlogView(UUID id, String title, String date, String url) {}
