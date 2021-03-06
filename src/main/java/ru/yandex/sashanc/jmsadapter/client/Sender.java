package ru.yandex.sashanc.jmsadapter.client;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import ru.yandex.sashanc.jmsadapter.dao.FilesDaoImpl;

import javax.jms.*;

public class Sender {
    private static final Logger logger = Logger.getLogger(Main.class);

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;

    public Sender(){
        factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        try {
            connection = factory.createConnection();
            connection.setClientID("clientSender");
            session = connection.createSession(false, session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("myQueue");
            producer = session.createProducer(destination);
            connection.start();
        } catch (JMSException e) {
            logger.info("context", e);
        }
    }

    public void sendMessage(String message) throws JMSException {
        logger.info("Sender:sendMessage(String message) is launched...");
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(message);
        producer.send(textMessage);
    }

//    public void sendMessage(RequestDTO message) throws JMSException {
//        logger.info("Sender:sendMessage(String message) is launched...");
//        TextMessage textMessage = session.createTextMessage();
//        textMessage.setText(message);
//        producer.send(textMessage);
//    }
}

