package com.tuozq.study.rabbitmq;

import com.rabbitmq.client.*;
import com.tuozq.study.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * 消费者
 */
public class Consumer01 {

    public static void main(String[] args) throws IOException {
        // 处理一条消息耗时1秒
        new Consumer(1, false).start();
    }


}
