package com.example.finalproto.Service;

import com.example.finalproto.Entity.Chat;
import com.example.finalproto.Repository.ChatRepository;
import com.example.finalproto.dto.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    ChatRepository repo;


    public List<ChatDTO> get_chatList(String userID,String id){



    List<Chat> list_final= new ArrayList<>();
    List<Chat> list_to=repo.findAllBySenderAndAndReciver(userID,id);
    List<Chat> list_from=repo.findAllBySenderAndAndReciver(id,userID);
    list_final.addAll(list_to);
    list_final.addAll(list_from);
    List<ChatDTO> dtoList = new ArrayList<>();
        for (Chat chat: list_final
             ) {
            ChatDTO dto = entityToDto(chat);
            dtoList.add(dto);


        }
    return dtoList;

    }
    @Override
    public void message_send(ChatDTO dto) {

     Chat chat=dtoToEntity(dto);

     repo.save(chat);
    }


}
