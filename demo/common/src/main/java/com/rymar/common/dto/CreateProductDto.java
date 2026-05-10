package com.rymar.common.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductDto (String name, UUID uuid , BigDecimal price, int count){}
