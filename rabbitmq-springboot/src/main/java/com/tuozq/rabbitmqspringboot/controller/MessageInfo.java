package com.tuozq.rabbitmqspringboot.controller;

import lombok.Data;
import org.springframework.amqp.core.Message;

import java.io.Serializable;
import java.util.Date;

@Data
public class MessageInfo implements Serializable {

    

    String id;

    String content;

    Date createTime;

    MessageInfo(String id, String content, Date createTime){
        this.id = id;
        this.content = content;
        this.createTime = createTime;
    }

    MessageInfo(){};




}
