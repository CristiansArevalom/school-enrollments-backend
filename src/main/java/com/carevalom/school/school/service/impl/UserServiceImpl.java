package com.carevalom.school.school.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carevalom.school.school.exception.ModelNotFoundException;
import com.carevalom.school.school.model.Role;
import com.carevalom.school.school.model.User;
import com.carevalom.school.school.repository.IGenericRepository;
import com.carevalom.school.school.repository.IUserRepo;
import com.carevalom.school.school.service.IRoleService;
import com.carevalom.school.school.service.IUserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDServiceImpl<User,Integer> implements IUserService{
    
    private final IUserRepo repo;
    private final IRoleService roleService;

    @Override
    protected IGenericRepository<User, Integer> getRepo() {
        return repo;
    }

    @Transactional
    @Override
    public boolean assignRoleToUser(Integer userId, Integer rolId) throws Exception{
        User user = this.findById(userId);
        Role rol = roleService.findById(rolId);
        //pdt personalizar error de que no existen ID y de que el rol ya esta asignado
        if(!user.getRoles().contains(rol)){
            user.getRoles().add(rol);
            repo.save(user);
            return true;
        }
        return false;
    }
    
    
}
