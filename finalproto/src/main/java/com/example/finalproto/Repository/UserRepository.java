package com.example.finalproto.Repository;

import com.example.finalproto.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,String> {

    public User findByPwdAndId(String id, String pwd);

    public List<User> findAll();

}
