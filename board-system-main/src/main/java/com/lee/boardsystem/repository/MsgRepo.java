package com.lee.boardsystem.repository;

import com.lee.boardsystem.model.Msg;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MsgRepo extends JpaRepository<Msg,Integer>{

}
