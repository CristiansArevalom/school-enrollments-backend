package com.carevalom.school.school.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carevalom.school.school.dto.RolDTO;
import com.carevalom.school.school.dto.UserDto;
import com.carevalom.school.school.model.User;
import com.carevalom.school.school.service.IUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    private final IUserService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

@GetMapping
public ResponseEntity<List<UserDto>> findAll() throws Exception{
    List<UserDto> lst = service.findAll().stream().map(obj -> convertEntityToDto(obj)).collect(Collectors.toList());
    return new ResponseEntity<List<UserDto>>(lst, HttpStatus.OK);
}

@PostMapping
public ResponseEntity<UserDto> save (@Valid @RequestBody UserDto dto) throws Exception{
    log.info(dto.toString());
    //VALIDAR, al hacer un insert y fallar role, por el transacional ya queda usado el ID
    User obj = service.save(convertDtoToEntity(dto));
    return new ResponseEntity<UserDto>(convertEntityToDto(obj), HttpStatus.CREATED);

}

@GetMapping("{id}/roles")
public ResponseEntity<List<RolDTO>> getUserRoles(@PathVariable("id") Integer id) throws Exception{
    List<RolDTO> lst = service.findById(id).getRoles().stream().map(obj -> mapper.map(obj,RolDTO.class)).collect(Collectors.toList());
    return new ResponseEntity<>(lst, HttpStatus.OK);
}


@PostMapping("/assingRole")
public ResponseEntity<Void> addUserRole(
           @RequestParam(value = "userId", required = true)Integer userId, 
        @RequestParam(value="roleId") Integer roleId) throws Exception{
    
    boolean success = service.assignRoleToUser(userId,roleId);
    if(success){
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }else{
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}

//ESTA PENDIENTE LO DE ASIGNAR EL ROL A UN USUARIO, VER EN DONDE SE DEJA ESA FUNCION



private UserDto convertEntityToDto(User obj){
    return mapper.map(obj,UserDto.class);
}
private User convertDtoToEntity(UserDto dto){
    return mapper.map(dto, User.class);

    }
}
