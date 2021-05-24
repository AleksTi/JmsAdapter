package ru.yandex.sashanc.jmsadapter;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.yandex.sashanc.jmsadapter.jms.JmsListener;
import ru.yandex.sashanc.jmsadapter.jms.JmsReceiver;

import javax.jms.JMSException;
import java.util.Scanner;

public class Main2 {
    private static final Logger logger = Logger.getLogger(Main2.class);

    public static void main(String[] args) {
        logger.info("Main2:main() is launched...");

        ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"});
        JmsReceiver receiver = (JmsReceiver) appContext.getBean("receiver");
//        try {
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
//            receiver.receiveMessage();
            scanner.nextLine();
//        } catch (JMSException e) {
//            logger.info("context", e);
//        }
    }
}
