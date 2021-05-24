package ru.yandex.sashanc.jmsadapter.client;

import org.apache.log4j.Logger;

import javax.jms.JMSException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Main:main() is launched...");
        Sender sender = new Sender();
        Receiver receiver = new Receiver();
        try {
            Scanner scanner = new Scanner(System.in);
            String message;
            while ((message = scanner.nextLine()) != null) {
                sender.sendMessage(message);
                String answer = receiver.receiveMessage();
                logger.info("Получен ответ: " + answer);
            }
        } catch (JMSException e) {
            logger.info("context", e);
        }
    }
}
