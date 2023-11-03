package com.carevalom.school.school.service;

import java.util.List;
import java.util.Map;

import com.carevalom.school.school.model.Enrollment;
import com.carevalom.school.school.model.EnrollmentDetail;

public interface IEnrollmentService extends ICRUDService<Enrollment,Integer>{
    Map<String, List<String>> getStudentsByCourse();
    EnrollmentDetail updateEnrollmentDetailClassRoom(String courseRoom,Integer id);

  
}
