package com.carevalom.school.school.service;

import java.util.List;

import com.carevalom.school.school.model.Student;

public interface IStudentService extends ICRUDService<Student,Integer> {
    
    List <Student> getByDescendingAge();
    
}
