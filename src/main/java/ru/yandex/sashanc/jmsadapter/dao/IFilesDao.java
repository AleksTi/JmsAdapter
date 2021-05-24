package ru.yandex.sashanc.jmsadapter.dao;

public interface IFilesDao {
    public void saveFileToDir(String file);
    public String getFileFromDir();
}
