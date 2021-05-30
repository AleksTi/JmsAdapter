package ru.yandex.sashanc.jmsadapter.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import ru.yandex.sashanc.jmsadapter.client.Main;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FilesDaoImpl implements IFilesDao  {
    private static final Logger logger = Logger.getLogger(Main.class);

    @Override
    public void saveFileToDir(String filePathToSave, Document xmlDocument) {
        TransformerFactory factory = TransformerFactory.newInstance();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        File xmlFile = new File(filePathToSave, LocalDateTime.now().format(dtf) + ".xml");
        try (FileOutputStream outStream = new FileOutputStream(xmlFile)) {
            Transformer transformer = factory.newTransformer();
            transformer.transform(new DOMSource(xmlDocument), new StreamResult(outStream));
        } catch (TransformerException | IOException e) {
            logger.info("context", e);
        }
    }

    @Override
    public Document getXmlDocFromXmlFile(String xmlFilePath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document xmlDoc = builder.parse(xmlFilePath);
            logger.info("XmlDoc_getElementsByTagName_\"Name\": " + xmlDoc.getElementsByTagName("Name").item(0).getTextContent());
            logger.info("XmlDoc_getElementsByTagName_\"Code\": " + xmlDoc.getElementsByTagName("Code").item(0).getTextContent());
            logger.info("XmlDoc_getElementsByTagName_\"AddInfo\": " + xmlDoc.getElementsByTagName("AddInfo").item(0).getTextContent());
            return xmlDoc;
        } catch (Exception e) {
            logger.info("context", e);
        }
        return null;
    }
}
