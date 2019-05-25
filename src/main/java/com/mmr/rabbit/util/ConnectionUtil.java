package com.mmr.rabbit.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:  建立rabbitmq链接
 * @Author: wuly
 * @CreateDate: 2019/5/12 11:21
 */
public class ConnectionUtil {
    
    /**
     * @Description: 获取mq的连接
     * @Author: wuly
     * @return: com.rabbitmq.client.Connection
     * @CreateDate: 2019/5/12 13:08
     */
    public static Connection getConnection() {
        Connection connection = null;
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置服务地址 IP
        factory.setHost("127.0.0.1");
        // 设置端口 AMQP 5672
        factory.setPort(5672);
        // 设置 vhost
        factory.setVirtualHost("/vhost");
        // 设置用户名 和 密码
        factory.setUsername("wly");
        factory.setPassword("wly0908");

        try {
            // 创建链接
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }

        /**
         * @Description:  关闭连接
         * @Author: wuly
         * @param connection : 连接
         * @return: void
         * @CreateDate: 2019/5/12 13:44
         */
    public static void closeConection(Connection connection){
        try {
            if (null != connection) {
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
