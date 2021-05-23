package ru.yandex.sashanc.jmsadapter;

import javax.jms.JMSException;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
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
