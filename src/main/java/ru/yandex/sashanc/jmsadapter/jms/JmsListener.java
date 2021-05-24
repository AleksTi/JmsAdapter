package ru.yandex.sashanc.jmsadapter.jms;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.sashanc.jmsadapter.Main2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JmsListener implements MessageListener {
    private static final Logger logger = Logger.getLogger(Main2.class);

    @Autowired
    private JmsSender sender;

    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        try {
            logger.info("Following message is received: " + msg.getText());
            sender.sendMessage("Message is received by JmsServer: " + msg.getText());
        } catch (JMSException e) {
            logger.info("context", e);
        }
    }
}
