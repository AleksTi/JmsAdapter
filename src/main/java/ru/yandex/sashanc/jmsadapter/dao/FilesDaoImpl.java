package ru.yandex.sashanc.jmsadapter.dao;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import ru.yandex.sashanc.jmsadapter.client.Main;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import static java.nio.file.Files.readAllLines;

public class FilesDaoImpl implements IFilesDao  {
    private static final Logger logger = Logger.getLogger(Main.class);

    @Override
    public void saveFileToDir(String file) {
        logger.info("File has been written");
    }

    @Override
    public String getXMLDocFromFile(String filePath) {
        String fileName = FilesDaoImpl.convertXMLFileToXMLDocument(filePath).getLocalName();
        return "I'm file from dir" + fileName;
    }

    public static Document convertXMLFileToXMLDocument(String filePath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            return builder.parse(filePath);
        } catch (Exception e) {
            logger.info("context", e);
        }
        return null;
    }

    public static String getStringXML(Document xmlDocument) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(xmlDocument), new StreamResult(stringWriter));
            String stringXmlDoc = stringWriter.toString();
//            logger.info("FilesDaoImpl:getStringXML(Document xmlDocument) " + stringWriter.toString());
            return stringXmlDoc;

        } catch (TransformerException e) {
            logger.info("context", e);
        }
        return null;
    }

//            Path path = Paths.get(filePath);
//            List<String> lines = Files.readAllLines(path);
//            for (String s : lines) {
//                logger.info(s);
//            }

}
