package com.rymar.common.dto;

import java.math.BigDecimal;

public record CreateProductDto (String name, BigDecimal price, int count){}
