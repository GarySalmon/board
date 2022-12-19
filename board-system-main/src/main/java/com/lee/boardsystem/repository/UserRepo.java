package com.lee.boardsystem.repository;

import com.lee.boardsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
//    List<User> findByUser_id(@Param("user_id") Integer user_id);

}
