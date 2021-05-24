package ru.yandex.sashanc.jmsadapter.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import ru.yandex.sashanc.jmsadapter.Main2;

import javax.jms.*;

public class JmsReceiver {
    private static final Logger logger = Logger.getLogger(Main2.class);

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
            consumer.setMessageListener(listener);
            connection.start();
            while (true){
                Thread.sleep(1000);
            }
        } catch (JMSException | InterruptedException e) {
            logger.info("context", e);
        }
    }

//    public void receiveMessage() throws JMSException {
//        connection.start();
//        TextMessage message = (TextMessage) consumer.receive();
//        logger.info("========================================================");
//        logger.info(message.getText());
//        connection.close();
//    }
}
