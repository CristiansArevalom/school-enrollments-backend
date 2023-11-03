package com.carevalom.school.school.model;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idEnrollment;

    @Column(nullable = false)
    private LocalDateTime enrollmentDate;
    
    @ManyToOne
    @JoinColumn(name="id_student",nullable = false,foreignKey = @ForeignKey(name="ENROLLMET_ID_STUDENT_FK"))
    private Student student;

    @Column(nullable = false)
    private boolean status;


    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<EnrollmentDetail> enrollmentDetails; //el error estaba aqui Ddebe cuadar con el nomre del DTo
    


    

}
