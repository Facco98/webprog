package ex3simplehttpd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;

public class HttpDeamon {

    public static void main(String[] args){


        try{

            Integer portNumber = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Listening on " + InetAddress.getLocalHost().getHostAddress() + ":" + portNumber);
            while(true)
                new HttpConnectionThread(HttpDeamon::handleRequest, serverSocket.accept());
        } catch (NumberFormatException ex ) {
            System.err.println(args[1] + " is not a valid number");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IndexOutOfBoundsException ex){
            System.err.println("Usage: httpd <port>");

        }

    }

    public static void handleRequest(Socket socket){

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var read = Optional.empty();
            while( ! (read = Optional.ofNullable(input.readLine())).orElse("/end/").equals("/end/") )
                System.out.println(read.orElse(""));
            System.out.println("----------");
            input.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally{

            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }

        }

    }

}
