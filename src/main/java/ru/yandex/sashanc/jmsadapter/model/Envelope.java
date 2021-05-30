package ru.yandex.sashanc.jmsadapter.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "envelope")
public class Envelope {
    private String name;
    private int code;
    private String addInfo;

    public Envelope() {
    }

    public Envelope(String name, int code, String addInfo) {
        this.name = name;
        this.code = code;
        this.addInfo = addInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", addInfo='" + addInfo + '\'' +
                '}';
    }
}
