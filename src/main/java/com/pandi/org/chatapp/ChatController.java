package com.pandi.org.chatapp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/sendMessage")
    public Message addToConnection(@Payload Message message,SimpMessageHeaderAccessor simpMessageHeaderAccessor){

          simpMessagingTemplate.convertAndSend("/topic/public",message);
          return message;
    }

    @MessageMapping("/addUser")
    public Message addUser(@Payload Message message,SimpMessageHeaderAccessor simpMessageHeaderAccessor){

        List<String> chatUsers = ChatUsers.getInstance();
        chatUsers.add(message.sender);

        simpMessageHeaderAccessor.getSessionAttributes().put("username",message.getSender());

        simpMessagingTemplate.convertAndSend("/topic/public",message);
        return message;
    }




}
