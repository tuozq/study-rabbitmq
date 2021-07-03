package com.tuozq.study.rabbitmq;

import java.io.IOException;

/**
 * 消费者
 */
public class Consumer02 {

    public static void main(String[] args) throws IOException {
        // 处理一条消息耗时3秒
        new Consumer(3, false).start();
    }



}
