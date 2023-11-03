package com.carevalom.school.school.controller;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carevalom.school.school.dto.RolDTO;
import com.carevalom.school.school.model.Role;
import com.carevalom.school.school.service.IRoleService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {
    private final IRoleService service;
    private final ModelMapper mapper;

//getRoles

@GetMapping
public ResponseEntity<List<RolDTO>>readAll() throws Exception{
    List<RolDTO> lst = service.findAll().stream().map(obj->convertEntityToDto(obj)).collect(Collectors.toList());
    return new ResponseEntity<List<RolDTO>>(lst, HttpStatus.OK);
}

@PostMapping()
public ResponseEntity<RolDTO> save(@RequestBody RolDTO dto) throws Exception{
    Role obj= service.save(convertDtoToEntity(dto));
    return new ResponseEntity<RolDTO>(convertEntityToDto(obj),HttpStatus.CREATED);
}

private Role convertDtoToEntity(RolDTO dto){
    return mapper.map(dto, Role.class);
}
private RolDTO convertEntityToDto(Role obj){
    return mapper.map(obj, RolDTO.class);
}
    
}
