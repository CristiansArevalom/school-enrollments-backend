package com.carevalom.school.school.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.carevalom.school.school.exception.ModelNotFoundException;
import com.carevalom.school.school.model.Enrollment;
import com.carevalom.school.school.model.EnrollmentDetail;
import com.carevalom.school.school.repository.IEnrollmentRepo;
import com.carevalom.school.school.repository.IGenericRepository;
import com.carevalom.school.school.service.IEnrollmentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EnrollmentServiceImpl extends CRUDServiceImpl<Enrollment,Integer> implements IEnrollmentService{

    private final IEnrollmentRepo repo;

    @Override
    protected IGenericRepository<Enrollment, Integer> getRepo() {
        return this.repo;
        }


        /**Mostrar la relación de cursos matriculados y sus estudiantes correspondientes
         *  usando programación funcional 
         * (sugerencia, usar un Map<K,V>) */
    @Override
    public Map<String, List<String>> getStudentsByCourse() {
            Stream<Enrollment> enrollStream=repo.findAll().stream();
            Stream<List<EnrollmentDetail>> lsEnrollDetailStream = enrollStream.map(Enrollment::getEnrollmentDetails);
            Stream<EnrollmentDetail> enrollDetailStream =  lsEnrollDetailStream.flatMap(list-> list.stream());
            Map<String, List<String>> studentsByCourse = enrollDetailStream.collect(Collectors.groupingBy(s->s.getCourse().getName(), Collectors.mapping(t->t.getEnrollment().getStudent().getName()+" "+t.getEnrollment().getStudent().getLastName(), Collectors.toList())));
        return studentsByCourse;
    }

        @Transactional
        @Override
        //http://127.0.0.1:8080/api/enrollment/enrollmentDetail?courseRoom=testEdit2&idEnrollmentDetail=13
        public EnrollmentDetail updateEnrollmentDetailClassRoom(String courseRoom, Integer id) {
            repo.updateEnrollmentDetailClassRoom(courseRoom, id);
            //traer el enrollmentDetail que tenga ese id sin usar un repository especifico de enrollmentDetail
            Stream<Enrollment>erollStream = repo.findAll().stream();
            Stream<List<EnrollmentDetail>> lsEnrollDetailStream = erollStream.map(e -> e.getEnrollmentDetails());
            Stream<EnrollmentDetail> enrollDetailStream = lsEnrollDetailStream.flatMap(list->list.stream());
            EnrollmentDetail updateEnrollment = enrollDetailStream.filter(s -> s.getIdEnrollmentDetail()==id).findFirst().orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id));
            return updateEnrollment;
        }
    
     
        
}
