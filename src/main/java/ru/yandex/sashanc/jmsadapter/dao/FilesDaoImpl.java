package ru.yandex.sashanc.jmsadapter.dao;

import org.apache.log4j.Logger;
import ru.yandex.sashanc.jmsadapter.client.Main;

public class FilesDaoImpl implements IFilesDao  {
    private static final Logger logger = Logger.getLogger(Main.class);

    @Override
    public void saveFileToDir(String file) {
        logger.info("File has been written");
    }

    @Override
    public String getFileFromDir() {
        return "I'm file from dir";
    }

}
