package com.mmr.rabbit.simple;

import com.mmr.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Customer介绍：消息消费者
 *
 * @author: wuly
 * @date: 2019/5/12 14:00
 */
public class Customer {

    private static String QUEUE_NAME = "test.queue.simple";

    /**
     * @Description:  消费消息
     * @Author: wuly
     * @return: void
     * @CreateDate: 2019/5/12 14:31
     */
    public void custome(){
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = null;
        try {
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
            // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
            Consumer consumer = new DefaultConsumer(channel) {
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Customer Received '" + message + "'");
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.custome();
    }
}
