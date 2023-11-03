package com.carevalom.school.school.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class StudentDto {

    
    private Integer idStudent;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255, message = "name min 3")
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50, message = "lastName min 3")
    private String lastName;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50, message = "dni min 3")
    private String dni;

    @NotNull
    @Min(value = 1, message = "age must be higher than 1")
    @Max(value = 99, message = "age must be lower than 99")
    private Integer age;
}
