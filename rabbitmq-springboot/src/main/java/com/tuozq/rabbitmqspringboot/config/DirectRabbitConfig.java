package com.tuozq.rabbitmqspringboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    public static final String DIRECT_QUEUE1 = "directQueue1";

    /**
     * 定义一个队列1
     * @return
     */
    @Bean
    public Queue directQueue1() {
        return new Queue(DIRECT_QUEUE1);
    }

    @Bean
    public DirectExchange directExchange1(){
        return new DirectExchange("directExchange1", true, false);
    }

    @Bean
    public Binding bindingDirectExchange(){
        return BindingBuilder.bind(directQueue1()).to(directExchange1()).with("sms");
    }

}
