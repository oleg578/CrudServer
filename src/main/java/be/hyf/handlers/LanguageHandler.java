package be.hyf.handlers;

import be.hyf.entities.Language;
import be.hyf.helpers.Db;
import be.hyf.helpers.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LanguageHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // http method
        String method = exchange.getRequestMethod();
        if (!method.equals("GET")) {
            exchange.sendResponseHeaders(405, 0);
            exchange.close();
            return;
        }
        String response = "";
        List<Language> languagesList = new ArrayList<>();
        String filePath = "conf" + File.separator + "db.properties";
        // set Content-Type application/json
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        try (Connection conn = Db.getConnection(filePath)) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT language_id, name, last_update FROM language");
            ResultSet languages = stmt.executeQuery();
            while (languages.next()) {
                Language language = new Language(
                        languages.getInt("language_id"),
                        languages.getString("name").trim(),
                        languages.getTimestamp("last_update")
                );
                languagesList.add(language);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String languagesJson = objectMapper.writeValueAsString(languagesList);
            exchange.sendResponseHeaders(200, languagesJson.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(languagesJson.getBytes());
            }

        } catch (SQLException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            ObjectMapper objectMapper = new ObjectMapper();
            String errorMessageJson = objectMapper.writeValueAsString(errorMessage);
            exchange.sendResponseHeaders(500, errorMessageJson.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(errorMessageJson.getBytes());
            }
        }
    }
}
