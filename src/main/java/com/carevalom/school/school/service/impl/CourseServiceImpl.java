package com.carevalom.school.school.service.impl;

import org.springframework.stereotype.Service;

import com.carevalom.school.school.model.Course;
import com.carevalom.school.school.repository.ICourseRepo;
import com.carevalom.school.school.repository.IGenericRepository;
import com.carevalom.school.school.service.ICourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDServiceImpl<Course,Integer> implements ICourseService {

    public final ICourseRepo repo;

    @Override
    protected IGenericRepository<Course,Integer>getRepo() {
        return this.repo;
    }

    
}
