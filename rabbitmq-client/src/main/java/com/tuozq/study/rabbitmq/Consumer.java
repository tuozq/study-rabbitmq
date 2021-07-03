package com.tuozq.study.rabbitmq;

import com.rabbitmq.client.*;
import com.tuozq.study.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * 消费者
 */
public class Consumer {

    /**
     * 处理消息耗时/单位秒
     */
    private int costTime;

    /**
     * 是否开启自动应答
     */
    private boolean autoAck;

    Consumer(){
        this.costTime = -1;
    }

    Consumer(int costTime, boolean autoAck){
        this.costTime = costTime;
        this.autoAck = autoAck;
    }

    public void start() throws IOException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 获取通道
        final Channel channel = connection.createChannel();
        // 定义一个消息队列
        String queue = "queue2";

        // Declare a queue
        // @param queue  消息队列的名称
        // @param durable 是否将队列持久化到磁盘
        // @param exclusive 是否独占
        // @param autoDelete 当队列中消息被消费完并且消费者的连接关闭时，是否自动删除队列
        // @param arguments 额外附加参数
        if(!autoAck){
            channel.basicQos(1);
        }
        channel.basicQos(1);
        channel.queueDeclare(queue, true, false, false, null);
        channel.basicConsume(queue, autoAck, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if(costTime > 0){
                    sleep();
                }
                if(!autoAck){
                    // 未开启自动应答，需手动应答
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
                System.out.println("接收到消息：" + new String(body));
            }
        });
    }

    private void sleep(){
        try {
            Thread.sleep(this.costTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
