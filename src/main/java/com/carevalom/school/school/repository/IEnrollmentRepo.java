package com.carevalom.school.school.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carevalom.school.school.model.Enrollment;

@Repository
public interface IEnrollmentRepo extends IGenericRepository<Enrollment,Integer>{

    @Modifying
    //@Query(value = "UPDATE enrollment_detail SET class_room=:classRoom WHERE id_enrollment_detail=:id", nativeQuery = true)
    @Query("Update EnrollmentDetail ed set ed.classRoom=:classRoom WHERE ed.idEnrollmentDetail=:id")
    Integer updateEnrollmentDetailClassRoom(@Param("classRoom")String classRoom, @Param("id") Integer idEnrollmentDetail);

    
}
