package com.tuozq.rabbitmqspringboot.comsumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.tuozq.rabbitmqspringboot.config.DirectRabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

//@Component
@RabbitListener(queues = DirectRabbitConfig.DIRECT_QUEUE1)
public class MessageConsumer02 {

    @RabbitHandler
    public void handler(@Payload String msg, Channel channel, Message message) throws IOException, InterruptedException {
        System.out.print("消费者02：");
        System.out.println(msg);
        Thread.sleep(2000);
        // 手动ack
         channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
    }

}
