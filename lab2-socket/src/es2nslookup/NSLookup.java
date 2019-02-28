package es2nslookup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Optional;

public class NSLookup {

    public static void main(String[] args) throws IOException {

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        Optional<String> input = Optional.empty();
        System.out.println("Inserisci il nome di cui effettuare il lookup, /end per terminare");
        while ( !(input = Optional.ofNullable(keyboard.readLine())).orElse("/end").trim().equals("/end") ){

            if(input.get().trim().equals(""))
                input = Optional.of("localhost");
            InetAddress[] hosts = InetAddress.getAllByName(input.get().trim());
            System.out.println("*** Results for " + input.get().trim());
            for( InetAddress i : hosts ) {
                if (i instanceof Inet4Address) {
                    System.out.println("Host: " + i.getHostName());
                    System.out.println("Server address: " + i.getHostAddress());
                    System.out.println("---");
                }
            }
            System.out.println("");
            System.out.println("Inserisci il nome di cui effettuare il lookup, /end per terminare");

        }

    }

}
