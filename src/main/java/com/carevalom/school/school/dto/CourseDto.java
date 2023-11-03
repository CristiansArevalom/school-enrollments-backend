package com.carevalom.school.school.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private Integer idCourse;
    
    @NotNull
    @NotEmpty
    @Size(min=3,max=255,message="name min 3 length")
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 3 , max = 10 , message = "acronym min 3 length")
    private String acronym;
    
    @NotNull
    
    private boolean status;
    
}
