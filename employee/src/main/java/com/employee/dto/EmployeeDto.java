package com.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;

    @NotBlank
    @Size(min = 2, max = 25,message = "name should be min 2 char and max 25")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min=10,max=10,message = "mobile number only 10 digit")
    private String mobile;

}
