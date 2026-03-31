package com.petshop.catalog.web.faq;

import java.util.UUID;

public record FaqUpdateRequest(UUID id, String question, String answer, boolean isVisible) {}