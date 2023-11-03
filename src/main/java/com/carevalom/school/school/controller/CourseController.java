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

import com.carevalom.school.school.dto.CourseDto;
import com.carevalom.school.school.model.Course;
import com.carevalom.school.school.service.ICourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    
    private final ICourseService service;
    
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CourseDto>>findAll() throws Exception{
        List<CourseDto> list = service.findAll().stream().map(course->convertEntityToDTO(course)).collect(Collectors.toList());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> findById(@PathVariable("id") Integer id) throws Exception{
        Course obj= service.findById(id);
        return new ResponseEntity<>(convertEntityToDTO(obj),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CourseDto> save(@Valid @RequestBody CourseDto dto) throws Exception{
        Course obj =service.save(convertDtoToEntity(dto));
        return new ResponseEntity<>(convertEntityToDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(@Valid @PathVariable("id") Integer id, @RequestBody CourseDto dto) throws Exception{
        dto.setIdCourse(id);
        Course obj = service.update(convertDtoToEntity(dto), id);
        return new ResponseEntity<>(convertEntityToDTO(obj),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    private CourseDto convertEntityToDTO(Course obj){
        return mapper.map(obj, CourseDto.class);
    }

    private Course convertDtoToEntity(CourseDto dto){
        return mapper.map(dto,Course.class);
    }




}
