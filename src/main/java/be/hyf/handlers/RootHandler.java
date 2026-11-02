package be.hyf.handlers;

import be.hyf.helpers.Json;
import be.hyf.helpers.Message;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Arrays;

public class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // http method
        String method = exchange.getRequestMethod();
        if (!method.equals("GET")) {
            exchange.sendResponseHeaders(405, 0);
            exchange.close();
            return;
        }

//        String body = new String(exchange.getRequestBody().readAllBytes());
//        System.err.println(body);

        Json.sendObject(exchange, 200, new Message("Test CRUD Server"));
    }
}