package com.petshop.catalog.application.faq;

import java.util.UUID;

public record FaqView(UUID id, String question, String answer) {}