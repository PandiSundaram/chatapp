package com.pandi.org.chatapp;

import java.util.ArrayList;
import java.util.List;

public class ChatUsers {

    private static List<String> UserList = new ArrayList<>();

    private ChatUsers(){

    }

    public static List<String> getInstance(){
        if(UserList == null){
           return new ArrayList<>();
        }else{
           return UserList;
        }
    }

    public static void addUser(String userName){
        UserList.add(userName);
    }

}
