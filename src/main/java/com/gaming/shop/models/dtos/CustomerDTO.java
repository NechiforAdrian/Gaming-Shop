package com.gaming.shop.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    private long id;
    @NotBlank(message = "First name must not be blank!")
    @Size(min = 3, max = 25, message = "First name must contain between 3 and 25 characters!")
    private String firstName;
    @NotBlank(message = "Last name must not be blank!")
    @Size(min = 3, max = 25, message = "Last name must contain between 3 and 25 characters!")
    private String lastName;
    private int age;
    @NotBlank(message = "Email name must not be blank!")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Email is not valid")
    private String email;
    @NotBlank(message = "Address must not be blank!")
    @Size(min = 3, max = 100, message = "Address must have between 3 and 100 characters!")
    private String address;
    @NotBlank(message = "Phone number must not be blank!")
    @Size(min = 10, max = 15, message = "Phone number must have between 10 and 15 numbers!")
    private String phoneNumber;
}
