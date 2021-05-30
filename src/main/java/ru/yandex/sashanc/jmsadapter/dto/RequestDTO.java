package ru.yandex.sashanc.jmsadapter.dto;

import java.io.Serializable;

public class RequestDTO implements Serializable {
    private int id;
    private String command;
    private String xmlFilePath;
    private String xmlString;


    public RequestDTO(){
    }

    public RequestDTO(int id, String command, String xmlFilePath, String xmlString) {
        this.id = id;
        this.command = command;
        this.xmlFilePath = xmlFilePath;
        this.xmlString = xmlString;
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

    public String getXmlFilePath() {
        return xmlFilePath;
    }

    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public String getXmlString() {
        return xmlString;
    }

    public void setXmlString(String xmlString) {
        this.xmlString = xmlString;
    }
}
