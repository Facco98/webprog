package example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        while(true){

            Socket socket = serverSocket.accept();
            BufferedReader inFromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter outToSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String fromClient = inFromSocket.readLine().trim().toUpperCase();

            if( fromClient.equals("PING") )
                outToSocket.write("PONG");
            else
                outToSocket.write("PONG");

            outToSocket.newLine();
            outToSocket.flush();

            inFromSocket.close();
            outToSocket.close();
            socket.close();

        }

    }

}
