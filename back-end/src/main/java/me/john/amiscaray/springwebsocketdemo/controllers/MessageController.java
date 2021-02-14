package me.john.amiscaray.springwebsocketdemo.controllers;

import me.john.amiscaray.springwebsocketdemo.dtos.MessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public MessageDto getMessages(MessageDto dto){

        return dto;

    }

}
