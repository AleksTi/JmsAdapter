package ru.yandex.sashanc.jmsadapter.client;

import com.google.gson.Gson;
import org.w3c.dom.Document;

import ru.yandex.sashanc.jmsadapter.dao.FilesDaoImpl;
import ru.yandex.sashanc.jmsadapter.dto.RequestDTO;

import java.nio.file.Paths;

public class Service {

    private String executeRequest(int id, String command, String stringXmlDoc) {
        RequestDTO requestDTO = new RequestDTO(id, command, stringXmlDoc);
        Gson gson = new Gson();
        return gson.toJson(requestDTO);
    }

    public String checkConnection(){
        return executeRequest(1, "checkConnection", null);
    }

    public String getXMLFile(String requestedFilePath){
        return executeRequest(2, "getXMLFile", requestedFilePath);
    }

    public String sendXMLFile(String sentFilePath) {
        Document xmlDocument;
        String stringXmlDoc = null;
        if (Paths.get(sentFilePath).toFile().exists()) {
            xmlDocument = FilesDaoImpl.convertXMLFileToXMLDocument(sentFilePath);
            stringXmlDoc = FilesDaoImpl.getStringXML(xmlDocument);
        }
        return executeRequest(3, "sendXMLFile", stringXmlDoc);
    }
}
