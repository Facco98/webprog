package es1localname;

import java.net.*;

public class LocalHostName {

    public static void main(String[] args) throws UnknownHostException {

        InetAddress localMachine = InetAddress.getLocalHost();
        System.out.println("Local Host name: " + localMachine.getHostName());

    }

}
