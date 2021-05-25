package ru.yandex.sashanc.jmsadapter.client;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

public class Receiver {
    private static final Logger logger = Logger.getLogger(Main.class);

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;

    public Receiver(MessageListener listener) {
        factory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_BROKER_URL);
        try {
            connection = factory.createConnection();
            connection.setClientID("clientReceiver");
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("myQueueResponse");
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(listener);
            connection.start();
        } catch (JMSException e) {
            logger.info("context", e);
        }
    }

    public String receiveMessage() throws JMSException {
        logger.info("Receiver:receiveMessage() is launched...");
        TextMessage message = (TextMessage) consumer.receive();
        return (message.getText());
    }
}
