package ru.yandex.sashanc.jmsadapter.client;

import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener implements MessageListener {
    private static final Logger logger = Logger.getLogger(Main.class);

    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        try {
            logger.info("Получен ответ: " + msg.getText());
        } catch (JMSException e) {
            logger.info("context", e);
        }
    }
}
