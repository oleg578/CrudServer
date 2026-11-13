package be.hyf.handlers;

import be.hyf.helpers.Json;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class HeadersListHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // http method
        String method = exchange.getRequestMethod();
        if (!method.equals("GET")) {
            exchange.sendResponseHeaders(405, 0);
            exchange.close();
            return;
        }
        Headers headers = exchange.getRequestHeaders();

        Json.sendObject(exchange, 200, headers);
    }
}
