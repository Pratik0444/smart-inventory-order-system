package org.self.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductDto {
	
 private Long id;
 
@NotBlank(message = "Product name is required")
 private String name;
 
@Min(value =0, message = "Quantity cannot be negative")
 private int quantity;

@Positive(message = "Price must be greater than 0")
private double price;
}
