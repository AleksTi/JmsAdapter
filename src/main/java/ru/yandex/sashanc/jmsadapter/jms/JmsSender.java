package ru.yandex.sashanc.jmsadapter.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import ru.yandex.sashanc.jmsadapter.Main2;

import javax.jms.*;

public class JmsSender {
    private static final Logger logger = Logger.getLogger(Main2.class);

    private ConnectionFactory factory;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;

    public JmsSender(){
        factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        try {
            connection = factory.createConnection();
            connection.setClientID("sender");
            session = connection.createSession(false, session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("myQueueResponse");
            producer = session.createProducer(destination);
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws JMSException {
        logger.info("JmsSender:sendMessage(String message) is launched...");
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(message);
        producer.send(textMessage);
//        connection.close();
    }

}
