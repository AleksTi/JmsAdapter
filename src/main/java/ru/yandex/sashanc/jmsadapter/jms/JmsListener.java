package ru.yandex.sashanc.jmsadapter.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JmsListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        try {
            System.out.println("Following message is received: " + msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
