package com.carevalom.school.school.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.carevalom.school.school.dto.CourseDto;
import com.carevalom.school.school.exception.ModelNotFoundException;
import com.carevalom.school.school.model.Course;
import com.carevalom.school.school.service.ICourseService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


/*
tener en cuenta que en jwtRequestfilter y WebSecurityConfig se añadio
//@Profile(value = {"dev", "uat"})
para que esos perfiles sean los que se evaluen por jwt
*/
/*Se añade test ya que no esta definido en jwtRequestfilter ni webSEcirityConfig, porlo 
 * qeue no necesitara evaluar el JWT
 */
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
@WebMvcTest(CourseController.class)
public class CourseControllerTest {
    //no se usa @requiredAllcpmstructor
    @Autowired
    private MockMvc mockMvc; //mockMVC permire simular las llamadas HTTP

    @MockBean //esto crea una instancia ficticia de iCourseService para no usar autowired que trajera una instancia real
    private ICourseService service;
    
    @MockBean(name = "defaultMapper")
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFindAll() throws Exception{
        ////given
        Course COURSE_1 = new Course(1, "Programacion", "prog", true);
        Course COURSE_2 = new Course(2, "Ingles", "Iing", true);
        Course COURSE_3 = new Course(3, "Matematicas", "Math", true);

        CourseDto COURSEDTO_1 = new CourseDto(1, "Programacion", "prog", true);
        CourseDto COURSEDTO_2 = new CourseDto(2, "Ingles", "Iing", true);
        CourseDto COURSEDTO_3 = new CourseDto(3, "Matematicas", "Math", true);

        List<Course> courses = List.of(COURSE_1,COURSE_2,COURSE_3);
        /*
        Estas líneas de código establecen el comportamiento de los mocks antes de realizar la acción que deseas probar 
         */
        Mockito.when(service.findAll()).thenReturn(courses);
        Mockito.when(modelMapper.map(COURSE_1, CourseDto.class)).thenReturn(COURSEDTO_1); //como el controller convierde cada course a courseDTO en el stream con model mapper, se debe especificar el dato que retorna
        Mockito.when(modelMapper.map(COURSE_2, CourseDto.class)).thenReturn(COURSEDTO_2); //como el controller convierde cada course a courseDTO en el stream con model mapper, se debe especificar el dato que retorna
        Mockito.when(modelMapper.map(COURSE_3, CourseDto.class)).thenReturn(COURSEDTO_3); //como el controller convierde cada course a courseDTO en el stream con model mapper, se debe especificar el dato que retorna

        //when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/course"));

        //THEN
        response.
        andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].acronym",Matchers.is("Iing")));

    }

    void testFindById() throws Exception{
        ////given
        final int ID=1;
        Course COURSE_1 = new Course(1, "Programacion", "prog", true);
        CourseDto COURSEDTO_1 = new CourseDto(1, "Programacion", "prog", true);
        
        //when
        Mockito.when(service.findById(any())).thenReturn(COURSE_1);
        Mockito.when(modelMapper.map(COURSE_1,CourseDto.class)).thenReturn(COURSEDTO_1);
        
        //then
        mockMvc.perform(MockMvcRequestBuilders
            .get("/course/"+ID)
            .content(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Programacion")));
    }
    @Test
    void testSave() throws Exception{
        //given
        Course COURSE_2 = new Course(2, "Ingles", "Iing", true);
        CourseDto COURSEDTO_2 = new CourseDto(2, "Ingles", "Iing", true);

        //when
        Mockito.when(service.save(any())).thenReturn(COURSE_2);
        Mockito.when(modelMapper.map(COURSE_2, CourseDto.class)).thenReturn(COURSEDTO_2);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(COURSEDTO_2));

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(true)));

    }

    @Test
    void testUpdate() throws Exception{
        int ID = 2;
        Course COURSE_1 = new Course(1, "Programacion", "prog", true);
        CourseDto COURSEDTO_1 = new CourseDto(1, "Programacion", "prog", true);

        Mockito.when(service.update(any(),any())).thenReturn(COURSE_1);
        Mockito.when(modelMapper.map(COURSE_1,CourseDto.class)).thenReturn(COURSEDTO_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/courses/" + ID)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(COURSEDTO_1));

        mockMvc.perform(mockRequest)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(true)));

    }
    //Este metodo hace un test cuando el id no existe para ver si lanza ele rror
    @Test
    void testUpdateError() throws Exception{
        try{
        int ID = 99;
        CourseDto COURSEDTO_99 = new CourseDto(99, "ERROR", "ERROR", true);

        //aca se simula que cuando llame el servicio bajo un id, arroje el error 
        //Mockito.when(service.update(any(), any())).thenThrow(new ModelNotFoundException("ID NOT VALID "+ID));

        Mockito.when(service.update(any(), any())).thenThrow(new ModelNotFoundException("ID NOT VALID: " + ID));
        MockHttpServletRequestBuilder mockRequest =  MockMvcRequestBuilders
            .put("/courses/" + ID)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(COURSEDTO_99));

        mockMvc.perform(mockRequest)
            .andExpect(MockMvcResultMatchers.status().isNotFound());
        
            

            }catch(Exception ex){
                log.info("EEEEERRRRORRR: "+ex.getClass().getName());
            }

            //.andExpect(result -> assertTrue(result.getResolvedException() instanceof ModelNotFoundException));
        }
}
