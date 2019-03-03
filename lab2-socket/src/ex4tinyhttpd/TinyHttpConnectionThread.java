package ex4tinyhttpd;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.function.Predicate;


public class TinyHttpConnectionThread implements Runnable{

    private final Socket socket;
    private final Consumer<Socket> handler;
    public final Predicate<File> permissionCheck = TinyHttpConnectionThread::checkPermission;


    public TinyHttpConnectionThread(Socket socket) {

        this.socket = socket;
        this.handler = this::handleRequest;
        this.run();

    }

    public TinyHttpConnectionThread (Socket socket, Consumer<Socket> handler ){

        this.socket = socket;
        this.handler = handler;
        this.run();

    }

    @Override
    public void run() {

        this.handler.accept(this.socket);

    }

    private void handleRequest(Socket socket){

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var request = Optional.ofNullable(input.readLine()).orElse("");
            var tokenizer = new StringTokenizer(request);
            System.out.println(request);
            if(tokenizer.nextToken().equals("GET") && tokenizer.hasMoreTokens()) {

                var uri = tokenizer.nextToken();
                if( uri.endsWith("/") )
                    uri += "index.html";
                var requested = new File("."+uri);
                if( this.permissionCheck.test(requested) ){

                    if( !requested.exists() ) {
                        socket.getOutputStream().write("HTTP/1.1 404 Not Found\r\n".getBytes());
                        socket.getOutputStream().write("\r\n".getBytes());
                        socket.getOutputStream().write("404 Not Found".getBytes());
                    }
                    else{

                        if (requested.isDirectory()) {

                            this.serveDirectory(requested, socket);

                        } else {

                           this.serveFile(requested, socket);
                        }

                    }

                } else {

                    System.out.println("FALSE");
                    socket.getOutputStream().write("HTTP/1.1 401 Unauthorized\r\n".getBytes());
                    socket.getOutputStream().write("\r\n".getBytes());
                    socket.getOutputStream().write("Unauthorized\r\n".getBytes());

                }
            }
            else{

                socket.getOutputStream().write("HTTP/1.1 400 Bad Request\r\n".getBytes());

            }


        } catch (IOException e) {
            System.err.println("Error 1: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private static boolean checkPermission(File file){

        var currentPath = Paths.get("");
        System.out.println(currentPath);
        try {
            var filePath = Paths.get(file.getCanonicalPath());
            System.out.println(filePath);
            return filePath.toAbsolutePath().startsWith(currentPath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;



    }

    private void serveFile(File f, Socket socket) throws IOException {

        System.out.println("FILE: "+f.getAbsolutePath());
        var lines = Files.lines(Paths.get(f.getPath()));
        socket.getOutputStream().write("HTTP/1.1 200 OK\r\n".getBytes());
        socket.getOutputStream().write("Content-Type: text/html; charset=utf-8\r\n".getBytes());
        socket.getOutputStream().write("\r\n".getBytes());
        lines.forEachOrdered(line -> {

            try {
                socket.getOutputStream().write(line.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    private void serveDirectory(File f, Socket socket) throws IOException {

        System.out.println("DIR: " + f.getAbsolutePath());
        var files  = Optional.ofNullable(f.listFiles());
        var outputStream = socket.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
        outputStream.write("Content-Type: text/html; charset=utf-8\r\n".getBytes());
        outputStream.write("\r\n".getBytes());
        outputStream.write("<html><head></head><body>".getBytes());
        for( var file : files.orElse(new File[]{}) ){

            outputStream.write(file.getName().getBytes());
            outputStream.write("\n".getBytes());

        }
        outputStream.write("</body></html>\r\n".getBytes());



    }



}
