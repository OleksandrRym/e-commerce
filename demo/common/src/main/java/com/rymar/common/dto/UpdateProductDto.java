package com.rymar.common.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateProductDto(UUID id, String name, BigDecimal price, int count) {}
