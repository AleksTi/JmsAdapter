package ru.yandex.sashanc.jmsadapter;

import org.apache.log4j.Logger;
import ru.yandex.sashanc.jmsadapter.jms.JmsListener;
import ru.yandex.sashanc.jmsadapter.jms.JmsReceiver;

import javax.jms.JMSException;
import java.util.Scanner;

public class Main2 {
    private static final Logger logger = Logger.getLogger(Main2.class);

    public static void main(String[] args) {
        logger.info("Main2:main() is launched...");

        JmsReceiver receiver = new JmsReceiver(new JmsListener());
        try {
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            receiver.receiveMessage();
            scanner.nextLine();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
