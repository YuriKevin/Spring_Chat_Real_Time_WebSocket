package com.chat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.chat.Model.Message;

@Controller
public class WebSocketController {

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public Message chat(@DestinationVariable Long roomId, Message message){
        System.out.println(message);
        return new Message(message.getMessage(), message.getUser());
    }
}