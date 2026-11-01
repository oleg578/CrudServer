package be.hyf.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FaviconHandler implements HttpHandler {
    private static final String RESOURCE_PATH = "/favicon.ico";
    private final byte[] icon;

    public FaviconHandler() throws IOException {
        try (InputStream is = FaviconHandler.class.getResourceAsStream(RESOURCE_PATH)) {
            if (is == null) {
                throw new IOException("Resource not found on classpath: " + RESOURCE_PATH);
            }
            this.icon = is.readAllBytes();
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals("GET")) {
            exchange.sendResponseHeaders(405, -1);
            exchange.close();
            return;
        }
        exchange.getResponseHeaders().set("Content-Type", "image/x-icon");
        exchange.getResponseHeaders().set("Cache-Control", "public, max-age=86400");
        exchange.sendResponseHeaders(200, icon.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(icon);
        }
    }
}
