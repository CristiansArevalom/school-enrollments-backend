package com.carevalom.school.school.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)

public class EnrollmentDto {
    
    private Integer idEnrollment;
    
    @NotNull
    private StudentDto student;

    @NotNull
    private LocalDateTime enrollmentDate;

    @NotNull
    private boolean status;

    @JsonManagedReference
    @NotNull(message = "this field is required")
    private List<EnrollmentDetailDto> enrollmentDetails;
}

