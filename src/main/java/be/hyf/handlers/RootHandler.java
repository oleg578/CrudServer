package be.hyf.handlers;

import be.hyf.helpers.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

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
        // set Content-Type application/json
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        Message message = new Message("Test CRUD Server");
        ObjectMapper objectMapper = new ObjectMapper();
        String errorMessageJson = objectMapper.writeValueAsString(message);
        exchange.sendResponseHeaders(200, errorMessageJson.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(errorMessageJson.getBytes());
        }
    }
}