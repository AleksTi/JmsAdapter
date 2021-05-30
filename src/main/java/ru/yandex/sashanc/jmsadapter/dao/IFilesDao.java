package ru.yandex.sashanc.jmsadapter.dao;

import org.w3c.dom.Document;

public interface IFilesDao {
    public void saveFileToDir(String filePathToSave, Document xmlDocument);
    public Document getXmlDocFromXmlFile(String xmlFilePath);
}
