package com.pandi.org.chatapp;

import lombok.Data;

@Data
public class Message {

    String sender;
    Type type;
    String content;

}
