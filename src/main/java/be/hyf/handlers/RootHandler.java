package be.hyf.handlers;

import be.hyf.helpers.ErrorMessage;
import be.hyf.helpers.Json;
import be.hyf.helpers.Message;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
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
        try (InputStream in = exchange.getRequestBody()) {
            String body = new String(in.readAllBytes());
            String msg = !body.isEmpty() ? body : "the request doesn't have a body or it is empty";
           Message out = new Message(msg);
           Json.sendObject(exchange, 200, out);
        } catch (IOException e) {
            ErrorMessage err = new ErrorMessage("Internal Server Error: " + e.getMessage());
            Json.sendObject(exchange, 500, err);
        }

        Json.sendObject(exchange, 200, new Message("Test CRUD Server"));
    }
}