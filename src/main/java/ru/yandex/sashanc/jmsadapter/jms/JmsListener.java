package ru.yandex.sashanc.jmsadapter.jms;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.sashanc.jmsadapter.Main2;
import ru.yandex.sashanc.jmsadapter.dto.RequestDTO;
import ru.yandex.sashanc.jmsadapter.service.XmlHandlerService;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JmsListener implements MessageListener {
    private static final Logger logger = Logger.getLogger(Main2.class);

    @Autowired
    private XmlHandlerService xmlHandlerService;

    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        Gson gson = new Gson();
        RequestDTO request;
        try {
            request = gson.fromJson(msg.getText(), RequestDTO.class);
            xmlHandlerService.requestHandler(request);
        } catch (JMSException e) {
            logger.info("context", e);
        }
    }
}
