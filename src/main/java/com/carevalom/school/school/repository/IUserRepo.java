package com.carevalom.school.school.repository;

import com.carevalom.school.school.model.User;

public interface IUserRepo extends IGenericRepository<User, Integer>{
    
    //@Query("FROM User u WHERE u.username = :username")
    User findOneByUsername(String username);

}
