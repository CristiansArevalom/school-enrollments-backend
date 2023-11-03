package com.carevalom.school.school.model;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class EnrollmentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idEnrollmentDetail;

    @ManyToOne
    @JoinColumn(name = "id_enrollment",nullable = false, foreignKey = @ForeignKey(name = "ENROLLDETAIL_ID_ENROLL_FK"))
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name="id_course", nullable = false,foreignKey = @ForeignKey(name = "ENROLLDETAIL_ID_COURSE_FK"))
    private Course course;

    @Column(nullable = false, length = 255)
    private String classRoom;

}
