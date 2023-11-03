package com.carevalom.school.school.service.impl;

import java.util.List;
import java.util.Optional;

import com.carevalom.school.school.exception.ModelNotFoundException;
import com.carevalom.school.school.repository.IGenericRepository;
import com.carevalom.school.school.service.ICRUDService;

public abstract class CRUDServiceImpl<T, ID> implements ICRUDService<T,ID>{
    
    
    protected abstract IGenericRepository<T, ID> getRepo();


    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id));

    }

    @Override
    public T save(T t) throws Exception {
       return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        //getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND " + id));
        Optional<T> op =getRepo().findById(id);
        if(op.isEmpty() ){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
        }
        return getRepo().save(t);
    }

    @Override
    public void delete(ID id) throws Exception {
       getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND" + id));
       getRepo().deleteById(id);
    }

  
}
