package com.carevalom.school.school.service;


import com.carevalom.school.school.model.Course;
import com.carevalom.school.school.repository.ICourseRepo;
import com.carevalom.school.school.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import  org.junit.jupiter.api.Assertions;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class CourseServiceTest {

    @MockBean
    private CourseServiceImpl service; // se hubiera podido tambien con la interfaz private CourseService

    @MockBean
    private ICourseRepo repo;

    //la idea aqui e spara ordenar los diferentes atributos que los metodos test estan usando

    private Course COURSE_1;
    private Course COURSE_2;
    private Course COURSE_3;

    @BeforeEach //es para que se ejecute el metodo luego del constructor, luego d egenerar la instancia se ejecuta este metodo init
    public void init(){
        MockitoAnnotations.openMocks(this); //como service depende de la instancia del repo y ambas son somuladas con esto se pueden relacionar
        this.service = new CourseServiceImpl(repo); //  se crea la instancia del service y se le envia el repo para que exista la relacion
    }

    @Test
    void testFindAll() throws Exception {
        COURSE_1 = new Course(1, "Programacion", "prog", true);
        COURSE_2 = new Course(2, "Ingles", "Iing", true);
        COURSE_3 = new Course(3, "Matematicas", "Math", true);

        List<Course> courses = List.of(COURSE_1,COURSE_2,COURSE_3);

        Mockito.when(repo.findAll()).thenReturn(courses);

        List<Course> response =service.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isEmpty());
        Assertions.assertEquals(response.size(),3);
    }

     @Test
    void findById() throws Exception {
        int ID =1;
        COURSE_1 = new Course(1, "Programacion", "prog", true);
        Mockito.when(repo.findById(any())).thenReturn(Optional.of(COURSE_1));
        Course response = service.findById(ID);
        Assertions.assertNotNull(response);
     }

     @Test
    void testSave () throws Exception {
         COURSE_1 = new Course(1, "Programacion", "prog", true);
         Mockito.when(repo.save(any())).thenReturn(COURSE_1);
         Course response = service.save(COURSE_1);
         Assertions.assertNotNull(response);
     }

     @Test
     void testUpdate() throws Exception {
        int ID =1;
        COURSE_1 = new Course(1, "Programacion", "prog", true);
        Mockito.when(repo.findById(ID)).thenReturn(Optional.of(COURSE_1));
        Mockito.when(repo.save(any())).thenReturn(COURSE_1);
        Course response = service.update(COURSE_1,ID);
        Assertions.assertNotNull(response);
     }

     @Test
     void testDelete() throws Exception {
        //para los metodos que retornan void es mejor comprobar si se ejecuto N veces
        int ID = 1;
        repo.deleteById(ID);
        Mockito.verify(repo,Mockito.times(1)).deleteById(ID);

     }



}
