package com.imooc.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by lenovo on 2017/9/18.
 */
public class AppConsumer {
    private static final String url="tcp://localhost:61616";
    private static final String topicName="topic-test";

    public static void main(String[] args) throws JMSException {
        //1.创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //2.创建Connection
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5.创建一个目标
        Destination destination = session.createTopic(topicName);

        //6.创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //7.创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String Msg = textMessage.getText();
                    System.out.println("接受的消息为:"+Msg);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
