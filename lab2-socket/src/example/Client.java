package example;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8080);
        BufferedReader inFromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter outToSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        outToSocket.write("PING");
        outToSocket.newLine();
        outToSocket.flush();

        String fromServer = inFromSocket.readLine();
        System.out.println("<< " + fromServer);

        inFromSocket.close();
        outToSocket.close();
        socket.close();

    }

}
