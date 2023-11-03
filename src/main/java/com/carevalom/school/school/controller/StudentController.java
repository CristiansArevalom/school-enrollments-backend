package com.carevalom.school.school.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carevalom.school.school.dto.StudentDto;
import com.carevalom.school.school.model.Student;
import com.carevalom.school.school.service.IStudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/students")
public class StudentController {
 
    private final IStudentService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;    
    /* 
    ESta es la inyeccion que hace el @RequiredArgsConstructor
    private IStudentService service;
    public StudentController (IStudentService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }*/

@GetMapping
public ResponseEntity<List<StudentDto>> findAll() throws Exception{
    List<StudentDto> list =  service.findAll().stream().map(student ->convertEntityToDto(student)).collect(Collectors.toList());
    return new ResponseEntity<List<StudentDto>>(list,HttpStatus.OK);
}

@GetMapping("/{id}")

/*RequestParam vs PathVariable = 
 * @RequestParam extracts data from query parameters in the URL ?. 
@PathVariable extracts data from the URI path {id}.
*/
public ResponseEntity<StudentDto>findById(@PathVariable("id") Integer id) throws Exception{
    Student obj = service.findById(id);
    return new ResponseEntity<StudentDto>(convertEntityToDto(obj), HttpStatus.OK);
}

@PostMapping
public ResponseEntity<StudentDto> save (@Valid @RequestBody StudentDto dto) throws Exception{  
    Student obj= service.save(convertDToToEntity(dto));
    return new ResponseEntity<>(convertEntityToDto(obj),HttpStatus.CREATED);
}

@PutMapping("/{id}")
public ResponseEntity<StudentDto> update(@Valid @RequestBody StudentDto dto,@PathVariable("id") Integer id ) throws Exception{
    dto.setIdStudent(id);
    Student obj = service.update(convertDToToEntity(dto),id);
    return new ResponseEntity<StudentDto>(convertEntityToDto(obj), HttpStatus.OK);
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable("id")Integer id) throws Exception{
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

@GetMapping("/sortedByAge")
public ResponseEntity<List<StudentDto>> findAllSortedByAge(){

    List<StudentDto> lst = service.getByDescendingAge().stream().map(e->convertEntityToDto(e)).collect(Collectors.toList());
    return new ResponseEntity<>(lst,HttpStatus.OK);
}


private StudentDto convertEntityToDto(Student obj){
    return mapper.map(obj, StudentDto.class, "defaultMapper");
    /* ESto se evita utilizando la extension de mapper
    StudentDto dto = new StudentDto();
    dto.setId(obj.getId());
    dto.setName(obj.getName());
    dto.setLastName(obj.getLastName());
    dto.setDNI(obj.getDNI());
    dto.setAge(obj.getId());
    return dto;
     */
}


private Student convertDToToEntity(StudentDto dto){
    return mapper.map(dto,Student.class);
}


}
