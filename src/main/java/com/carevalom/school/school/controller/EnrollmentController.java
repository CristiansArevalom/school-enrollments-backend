package com.carevalom.school.school.controller;

import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carevalom.school.school.dto.EnrollmentDetailDto;
import com.carevalom.school.school.dto.EnrollmentDto;
import com.carevalom.school.school.model.Enrollment;
import com.carevalom.school.school.model.EnrollmentDetail;
import com.carevalom.school.school.service.IEnrollmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    
    private final IEnrollmentService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EnrollmentDto>>findAll() throws Exception{
        List<EnrollmentDto> lst=service.findAll().stream().map(obj-> convertEntityToDto(obj)).collect(Collectors.toList());
        return new ResponseEntity<List<EnrollmentDto>>(lst, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDto> findById(@PathVariable("id") Integer id) throws Exception{
        EnrollmentDto dto = convertEntityToDto(service.findById(id));
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<EnrollmentDto>save(@Valid @RequestBody EnrollmentDto dto) throws Exception{
        Enrollment obj =service.save(convertDtoToEntity(dto));
        return new ResponseEntity<EnrollmentDto>(convertEntityToDto(obj),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDto> update(@Valid @RequestBody EnrollmentDto dto,@PathVariable("id") Integer id) throws Exception{
        dto.setIdEnrollment(id);
        Enrollment obj = service.update(convertDtoToEntity(dto), id);
        return new ResponseEntity<>(convertEntityToDto(obj),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/studentsByCourse")
    public ResponseEntity<Map<String,List<String>>> studentsByCourse(){
        Map<String,List<String>> obj = service.getStudentsByCourse();
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @PutMapping("/enrollmentDetail")
    public ResponseEntity<EnrollmentDetailDto> updateCourseDetail(
        @RequestParam(value = "courseRoom", required = true)String courseRoom, 
        @RequestParam(value="idEnrollmentDetail") Integer id){
            EnrollmentDetail obj= service.updateEnrollmentDetailClassRoom(courseRoom, id);
            EnrollmentDetailDto dto = mapper.map(obj,EnrollmentDetailDto.class);
            return new ResponseEntity<EnrollmentDetailDto>(dto,HttpStatus.OK);
    }

    private EnrollmentDto convertEntityToDto(Enrollment obj){
        return mapper.map(obj,EnrollmentDto.class);
    }
    private Enrollment convertDtoToEntity(EnrollmentDto dto){
        return mapper.map(dto, Enrollment.class);
    }
    
}
