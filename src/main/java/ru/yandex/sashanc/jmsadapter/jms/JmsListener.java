package ru.yandex.sashanc.jmsadapter.jms;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.sashanc.jmsadapter.Main2;
import ru.yandex.sashanc.jmsadapter.dto.RequestDTO;

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
            Gson gson = new Gson();
            RequestDTO request = gson.fromJson(msg.getText(), RequestDTO.class);
            logger.info("Following message is received: id = " + request.getId());
            logger.info("Following message is received: command = " + request.getCommand());
            logger.info("Following message is received: xmlDocument = " + request.getXmlDocument());
//            logger.info("Following message is received: " + msg.getText());
            sender.sendMessage("Message is received by JmsServer: id = " + request.getId());
        } catch (JMSException e) {
            logger.info("context", e);
        }
    }
}
