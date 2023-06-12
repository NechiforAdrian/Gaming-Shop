package com.gaming.shop.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDTO {

    private long id;
    @NotBlank(message = "Product name must not be blank!")
    private String productName;
    @NotBlank(message = "Product brand must not be blank!")
    private String productBrand;
    @NotBlank(message = "Color must not be blank!")
    private String colour;
    @NotBlank(message = "Price is a mandatory field!")
    private double price;
}
