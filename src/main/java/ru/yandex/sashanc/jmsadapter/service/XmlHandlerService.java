package ru.yandex.sashanc.jmsadapter.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.yandex.sashanc.jmsadapter.Main2;
import ru.yandex.sashanc.jmsadapter.dao.FilesDaoImpl;
import ru.yandex.sashanc.jmsadapter.dto.RequestDTO;
import ru.yandex.sashanc.jmsadapter.jms.JmsSender;

import javax.jms.JMSException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

@Component
public class XmlHandlerService {
    private static final Logger logger = Logger.getLogger(Main2.class);

    @Autowired
    private JmsSender sender;

    @Autowired
    private FilesDaoImpl filesDao;

    public void requestHandler(RequestDTO request) {
        logger.info("Following message is received: id = " + request.getId());
        logger.info("Following message is received: command = " + request.getCommand());
        logger.info("Following message is received: getXmlFilePath = " + request.getXmlFilePath());
        logger.info("Following message is received: getXmlString = " + request.getXmlString());
        try {
            sender.sendMessage("Message is received by JmsServer: id = " + request.getId());
            if (request.getCommand().equals("checkConnection")) {
                sender.sendMessage("JmsServer: I'm Ok");
            }
            if (request.getCommand().equals("getXMLFile_code")){
                Document xmlDoc = filesDao.getXmlDocFromXmlFile(request.getXmlFilePath());
                int code = Integer.parseInt(xmlDoc.getElementsByTagName("Code").item(0).getTextContent());
                sender.sendMessage("JmsServer: Requested xmlFile.Code: " + code);
                logger.info("Code: " + code);
            }
            if (request.getCommand().equals("saveXmlFile")){
                filesDao.saveFileToDir(request.getXmlFilePath(), this.stringToXmlDoc(request.getXmlString()));
            }
        } catch (JMSException e) {
            logger.info("context", e);
        }
    }

    private Document stringToXmlDoc(String xmlDocument){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xmlDocument)));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.info("context", e);
        }
        return null;
    }
}
