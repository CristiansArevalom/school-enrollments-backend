package com.carevalom.school.school.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDetailDto {
    
    private Integer idEnrollmentDetail;

    @JsonBackReference
    private EnrollmentDto enrollment;

    @NotNull
    private CourseDto course;

    @NotNull
    private String classRoom;

}
