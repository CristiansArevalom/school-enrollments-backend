package com.carevalom.school.school.service.impl;

import org.springframework.stereotype.Service;

import com.carevalom.school.school.model.Role;
import com.carevalom.school.school.repository.IGenericRepository;
import com.carevalom.school.school.repository.IRoleRepo;
import com.carevalom.school.school.service.IRoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDServiceImpl<Role,Integer> implements IRoleService{

    private final IRoleRepo repo;
    @Override
    protected IGenericRepository<Role, Integer> getRepo() {
        return repo;
    }
    
}
