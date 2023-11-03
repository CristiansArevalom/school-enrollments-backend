package com.carevalom.school.school.repository;


import org.springframework.stereotype.Repository;

import com.carevalom.school.school.model.Course;


@Repository
public interface ICourseRepo extends IGenericRepository<Course, Integer>{ 
    
}
