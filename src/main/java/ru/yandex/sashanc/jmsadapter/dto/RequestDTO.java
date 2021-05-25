package ru.yandex.sashanc.jmsadapter.dto;

import java.io.Serializable;

public class RequestDTO implements Serializable {
    private int id;
    private String command;
    private String xmlDocument;

    public RequestDTO(){

    }

    public RequestDTO(int id, String command, String xmlDocument) {
        this.id = id;
        this.command = command;
        this.xmlDocument = xmlDocument;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getXmlDocument() {
        return xmlDocument;
    }

    public void setXmlDocument(String xmlDocument) {
        this.xmlDocument = xmlDocument;
    }
}
