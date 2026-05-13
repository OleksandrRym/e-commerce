package com.rymar.common.events;

import com.rymar.common.dto.UpdateProductDto;

public class UpdateProductEvent {
    public String topic = "product-events";
    public UpdateProductDto updateProductDto;
}
