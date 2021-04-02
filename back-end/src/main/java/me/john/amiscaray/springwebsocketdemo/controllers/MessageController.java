package me.john.amiscaray.springwebsocketdemo.controllers;

import me.john.amiscaray.springwebsocketdemo.dtos.MessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    // Handles messages from /app/chat. (Note the Spring adds the /app prefix for us).
    @MessageMapping("/chat")
    // Sends the return value of this method to /topic/messages
    @SendTo("/topic/messages")
    public MessageDto getMessages(MessageDto dto){

        return dto;

    }

}
