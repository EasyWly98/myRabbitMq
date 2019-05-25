package com.mmr.rabbit.simple;

import com.mmr.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @Description:  消息生产者
 * @Author: wuly
 * @CreateDate: 2019/5/12 13:48
 */
public class Productor {

    private static String QUEUE_NAME = "test.queue.simple";

    public void sendMsg(String mes) {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = null;
        try {
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            channel.basicPublish("", QUEUE_NAME, null, mes.getBytes());

            // 关闭连接
            channel.close();
            ConnectionUtil.closeConection(connection);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Productor productor = new Productor();
        Scanner scanner = new Scanner(System.in);
        while(true){
            productor.sendMsg(scanner.nextLine());
        }
    }
}
