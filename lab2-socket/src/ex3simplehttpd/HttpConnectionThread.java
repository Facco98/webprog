package ex3simplehttpd;

import java.net.Socket;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class HttpConnectionThread implements Runnable {

    private final Consumer<Socket> handler;
    private final Socket socket;

    public HttpConnectionThread( Consumer<Socket> handler, Socket socket ){

        this.handler = handler;
        this.socket = socket;
        this.run();

    }

    @Override
    public void run() {

        this.handler.accept(this.socket);

    }


}
