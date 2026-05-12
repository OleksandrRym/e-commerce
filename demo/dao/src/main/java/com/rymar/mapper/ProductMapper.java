package com.rymar.mapper;

import com.rymar.common.dto.CreateProductDto;
import com.rymar.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper{
    Product dtoToObject(CreateProductDto createProductDto);
}
