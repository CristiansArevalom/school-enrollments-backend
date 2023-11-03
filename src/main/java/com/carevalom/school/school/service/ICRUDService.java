package com.carevalom.school.school.service;

import java.util.List;
/*Se crea esta clase para evitar repetir este codigo en todoas las interfaces del service */
public interface ICRUDService <T,ID>{
    List<T> findAll() throws Exception;
    T findById(ID id) throws Exception;
    T save (T t ) throws Exception;
    T update(T t, ID id) throws Exception;
    void delete(ID id) throws Exception;
   }
