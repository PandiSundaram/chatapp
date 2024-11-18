package com.pandi.org.chatapp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;

@Configuration
@Slf4j

public class ListenerConfig {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;



    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket conncation...");
    }



        @EventListener
        public void handleWebSocketDisconnectLister(SessionDisconnectEvent event) {

            log.info("user disconnected event");

            StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//
            String username = (String) headerAccessor.getSessionAttributes().get("username");
           //List<String> chatUsers = ChatUsers.getInstance();

            if(username !=null) {
                log.info("User Disconnected:"+username);
                Message message = new Message();
                message.setSender(username);
                message.setType(Type.LEFT);
                messagingTemplate.convertAndSend("/topic/public", message);

            }
        }


}
