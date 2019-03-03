package ex4tinyhttpd;



import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class TinyHttpDeamon {

    public static void main(String args[]){

        try{

            Integer portNumber = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Listening on " + InetAddress.getLocalHost().getHostAddress() + ":" + portNumber);
            while(true)
                new TinyHttpConnectionThread(serverSocket.accept());
        } catch (NumberFormatException ex ) {
            System.err.println(args[1] + " is not a valid number");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IndexOutOfBoundsException ex){
            System.err.println("Usage: tinyhttpd <port>");

        }

    }

}
