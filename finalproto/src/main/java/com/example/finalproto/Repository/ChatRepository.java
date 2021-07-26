package com.example.finalproto.Repository;

import com.example.finalproto.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Long> {
    public List<Chat> findAllBySenderAndAndReciver(String sender,String reciver);
}
