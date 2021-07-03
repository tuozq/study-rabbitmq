package com.tuozq.study.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.tuozq.study.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

public class Producer {

    public static void main(String[] args) throws IOException {
        new Producer().sendMessage();
    }

    public void sendMessage() throws IOException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 定义一个消息队列
        String queue = "queue2";

        // Declare a queue
        // @param queue  消息队列的名称
        // @param durable 是否将队列持久化到磁盘
        // @param exclusive 是否独占
        // @param autoDelete 当队列中消息被消费完并且消费者的连接关闭时，是否自动删除队列
        // @param arguments 额外附加参数
        channel.queueDeclare(queue, true, false, false, null);
        String message = "hello, rabbitMQ.";
        // 发送消息
        // 当exchange交换机为空时，默认使用 (AMQP default) direct交换机，默认交换机交换隐式绑定到每个队列，路由key=队列名称
        // 将消息标记为持久性 - 通过将MessageProperties（实现BasicProperties）设置为值PERSISTENT_TEXT_PLAIN
        for (int i = 1; i <= 20; i++) {
            channel.basicPublish("", queue, MessageProperties.PERSISTENT_TEXT_PLAIN, (i + "  " +message).getBytes());
        }

    }

}
