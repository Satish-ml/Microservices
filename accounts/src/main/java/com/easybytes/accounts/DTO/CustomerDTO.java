package com.easybytes.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDTO {

    @NotEmpty(message = "Name canot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 to 30")
    @Schema(
            description = "Name of the customer", example = "Eazy Bytes"
    )
    private String name;

    @Schema(
            description = "Email address of the customer", example = "tutor@eazybytes.com"
    )
    @NotEmpty(message = "email address can't be empty")
    @Email(message = "email address should be a valid one")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDTO accountsDTO;
}
