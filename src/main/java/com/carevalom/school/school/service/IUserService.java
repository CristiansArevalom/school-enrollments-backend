package com.carevalom.school.school.service;
import com.carevalom.school.school.model.User;

public interface IUserService extends ICRUDService<User, Integer>{

    boolean assignRoleToUser(Integer userId, Integer rolId) throws Exception;

}
