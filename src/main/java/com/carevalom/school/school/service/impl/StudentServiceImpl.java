package com.carevalom.school.school.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.carevalom.school.school.model.Student;
import com.carevalom.school.school.repository.IGenericRepository;
import com.carevalom.school.school.repository.IStudentRepo;
import com.carevalom.school.school.service.IStudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //se inyecta la dependencia de repo por constructor
public class StudentServiceImpl extends CRUDServiceImpl<Student,Integer> implements IStudentService{
    
    private final IStudentRepo repo;

    @Override
    protected IGenericRepository<Student,Integer> getRepo() {
        return repo;
    }

    @Override
    public List <Student> getByDescendingAge() {
        List<Student> lst = repo.findAll().stream()
                .sorted(Comparator.comparing(s -> ((Student) s).getAge()).reversed())
                .collect(Collectors.toList());
        return lst;
        
    }

    /*select c.name,s.last_name ||' '||s.name Student
    from enrollment e inner join enrollment_detail ed
    on e.id_enrollment = ed.id_enrollment
    inner join student s
    on e.id_student = s.id_student
    inner join course c
    on ed.id_course = c.id_course
    group by c.name,Student; */


}
