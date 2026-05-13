package com.rymar.mapper;

import com.rymar.common.dto.CreateProductDto;
import com.rymar.common.dto.UpdateProductDto;
import com.rymar.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper{
    Product createProductDtoToObject(CreateProductDto createProductDto);
    Product updateProductDtoToObject(UpdateProductDto updateProductDto);
}
