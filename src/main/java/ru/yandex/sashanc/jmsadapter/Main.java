package ru.yandex.sashanc.jmsadapter;

import javax.jms.JMSException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sender sender = new Sender();
        try {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            sender.sendMessage(message);
            scanner.nextLine();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
