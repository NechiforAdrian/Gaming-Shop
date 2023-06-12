package com.gaming.shop.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderDTO {

    private long id;
    @NotBlank(message = "First name must not be blank!")
    @Size(min = 3, max = 25, message = "First name must contain between 3 and 25 characters!")
    private String firstName;
    @NotBlank
    private double totalPrice;
    @NotBlank(message = "Delivery address must not be blank!")
    private String deliveryAddress;
}
