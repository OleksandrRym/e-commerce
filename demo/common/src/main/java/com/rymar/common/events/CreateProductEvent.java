package com.rymar.common.events;

import com.rymar.common.dto.CreateProductDto;

public class CreateProductEvent {
  public String topic = "product-events";
  public CreateProductDto createProductDto;
}
