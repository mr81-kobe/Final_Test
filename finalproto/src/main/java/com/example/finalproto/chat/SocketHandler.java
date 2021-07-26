package com.example.finalproto.chat;

import com.example.finalproto.Service.ChatService;
import com.example.finalproto.Service.UserService;
import com.example.finalproto.dto.ChatDTO;
import com.example.finalproto.dto.RelationDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class SocketHandler extends TextWebSocketHandler {
    @Autowired
    ChatService service;

    HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
    List<WebSocketSession> wb_list = new ArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        String msg = message.getPayload();
        JSONParser parser = new JSONParser();
        try {
            JSONObject object =(JSONObject)parser.parse(msg);
            String to=(String)object.get("recive");
            String from_=(String)object.get("id");
            String mess=(String)object.get("text");
            String date =object.get("date").toString();
            System.out.println(date);
            System.out.println(object.get("date").getClass());
            ChatDTO dto = ChatDTO.builder().message(mess).to(to).from(from_).time(date).build();

            System.out.println(date);
            System.out.println(from_);
            service.message_send(dto);

        }catch (Exception e){
            e.printStackTrace();
        }


        for ( WebSocketSession webSocketSession: wb_list)
              {

        try {
            webSocketSession.sendMessage(new TextMessage(msg));
        } catch (Exception e) {
            e.printStackTrace();
        }

              }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //소켓 연결



        System.out.println("소캣 연결");

        System.out.println(session.getId());
        sessionMap.put(session.getId(), session);
        wb_list.add(session);
        
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //소켓 종료

        sessionMap.remove(session.getId());

    }
}