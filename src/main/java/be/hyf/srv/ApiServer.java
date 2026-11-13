package be.hyf.srv;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class ApiServer {
    private HttpServer server;
    private int port;

    public ApiServer init(int port) {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
            this.server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
            this.port = port;
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to bind server on port " + port, e);
        }
        return this;
    }

    public ApiServer addHandler(String path, HttpHandler handler) {
        server.createContext(path, handler);
        return this;
    }

    public void start() {
        server.start();
        System.out.println("Server started - http://localhost:" + port);
    }
}
