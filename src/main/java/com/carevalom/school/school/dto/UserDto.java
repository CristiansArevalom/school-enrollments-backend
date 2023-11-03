package com.carevalom.school.school.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    private Integer idUser;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String username;

    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 60)
    private String password;

    @NotNull
    private boolean enable;

    @NotNull(message = "this field is required")
    private List<RolDTO> roles;

}
