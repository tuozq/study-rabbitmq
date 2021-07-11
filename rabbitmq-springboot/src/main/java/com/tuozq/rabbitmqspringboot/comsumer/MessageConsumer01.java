package com.tuozq.rabbitmqspringboot.comsumer;

import com.rabbitmq.client.Channel;
import com.tuozq.rabbitmqspringboot.config.DirectRabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
@RabbitListener(queues = DirectRabbitConfig.DIRECT_QUEUE1)
public class MessageConsumer01 {

    @RabbitHandler
    public void handler(@Payload String msg, Channel channel, Message message) throws IOException, InterruptedException {
        System.out.print("消费者01：");
        System.out.println(msg);
        Thread.sleep(1000);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
