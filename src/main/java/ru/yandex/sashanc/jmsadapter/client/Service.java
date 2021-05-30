package ru.yandex.sashanc.jmsadapter.client;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import ru.yandex.sashanc.jmsadapter.dao.FilesDaoImpl;
import ru.yandex.sashanc.jmsadapter.dao.IFilesDao;
import ru.yandex.sashanc.jmsadapter.dto.RequestDTO;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.nio.file.Paths;

public class Service {
    private static final Logger logger = Logger.getLogger(Main.class);

    public String checkConnection(){
        logger.info("Service.checkConnection() is done...");
        return executeRequest(1, "checkConnection", null, null);
    }

    public String getXmlFile(String requestedFilePath){
        logger.info("Service.getXmlFile(String requestedFilePath) is done...");
        return executeRequest(2, "getXMLFile", requestedFilePath, null);
    }

    public String saveXmlFile(String filePathToSend, String filePathToSave) {
        Document xmlDocument;
        IFilesDao filesDao;
        String stringXmlDoc = null;
        if (Paths.get(filePathToSend).toFile().exists()) {
            filesDao = new FilesDaoImpl();
            xmlDocument = filesDao.getXmlDocFromXmlFile(filePathToSend);
            stringXmlDoc = this.getStringXML(xmlDocument);
        }
        logger.info("Service.saveXmlFile(String filePathToSend, String filePathToSave) is done...");
        return executeRequest(3, "saveXmlFile", filePathToSave, stringXmlDoc);
    }

    private String executeRequest(int id, String command, String xmlFilePath, String stringXml) {
        RequestDTO requestDTO = new RequestDTO(id, command, xmlFilePath, stringXml);
        Gson gson = new Gson();
        return gson.toJson(requestDTO);
    }

    /**
     * Метод преобразует XML Document в строку
     * @param xmlDocument - XML документ
     * @return строка типа String
     */
    private String getStringXML(Document xmlDocument) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(xmlDocument), new StreamResult(stringWriter));
            return stringWriter.toString();

        } catch (TransformerException e) {
            logger.info("context", e);
        }
        return null;
    }
}
