package com.tuozq.rabbitmqspringboot.controller;


import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @GetMapping("sendMessage")
    public String sendMessage(){
        MessageInfo messageInfo = new MessageInfo("001", "你好", new Date());
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("directExchange1", "sms", i + "：message");
        }

        return "ok";
    }

}
