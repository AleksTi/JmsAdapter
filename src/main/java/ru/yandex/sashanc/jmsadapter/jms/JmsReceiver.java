package ru.yandex.sashanc.jmsadapter.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsReceiver {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;

    public JmsReceiver(MessageListener listener){
        factory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        try {
            connection = factory.createConnection();
            connection.setClientID("receiver");
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("myQueue");
            consumer = session.createConsumer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage() throws JMSException {
        connection.start();
        TextMessage message = (TextMessage) consumer.receive();
        System.out.println("========================================================");
        System.out.println(message.getText());
        connection.close();
    }
}
