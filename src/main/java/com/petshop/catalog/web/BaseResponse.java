package com.petshop.catalog.web;

public record BaseResponse<T>(String message, T data) {}
