package be.hyf.handlers;

import be.hyf.entities.Film;
import be.hyf.entities.MPARating;
import be.hyf.helpers.Db;
import be.hyf.helpers.ErrorMessage;
import be.hyf.helpers.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // http method
        String method = exchange.getRequestMethod();
        if (!method.equals("GET")) {
            exchange.sendResponseHeaders(405, -1);
            exchange.close();
            return;
        }
        List<Film> filmList = new ArrayList<>();
        String confPath = "conf" + File.separator + "db.properties";
        try (Connection conn = Db.getConnection(confPath);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM film limit 30");
             ResultSet filmCursor = stmt.executeQuery()) {
            while (filmCursor.next()) {
                Film film = new Film();
                film.setId(filmCursor.getInt("film_id"));
                film.setTitle(filmCursor.getString("title"));
                film.setDescription(filmCursor.getString("description"));
                film.setReleaseYear(filmCursor.getInt("release_year"));
                film.setLanguageId(filmCursor.getInt("language_id"));
                film.setOriginalLanguageId(filmCursor.getObject("original_language_id", Integer.class));
                film.setRentalDuration(filmCursor.getInt("rental_duration"));
                film.setRentalRate(filmCursor.getBigDecimal("rental_rate"));
                film.setLength(filmCursor.getInt("length"));
                film.setReplacementCost(filmCursor.getBigDecimal("replacement_cost"));
                String rating = filmCursor.getString("rating");
                // DB values are "PG-13"/"NC-17"; valueOf() would only accept the enum constant names.
                film.setRating(rating == null ? null : MPARating.fromValue(rating));
                Array specialFeatures = filmCursor.getArray("special_features");
                if (specialFeatures != null) {
                    film.setSpecialFeatures((String[]) specialFeatures.getArray());
                }
                film.setLast_update(filmCursor.getTimestamp("last_update"));
                filmList.add(film);
            }
        } catch (SQLException e) {
            Json.sendObject(exchange, 500, new ErrorMessage(e.getMessage()));
            return;
        }

        try {
            Json.sendJson(exchange, 200, Json.MAPPER.writeValueAsString(filmList));
        } catch (JsonProcessingException e) {
            Json.sendObject(exchange, 500, new ErrorMessage("Failed to serialize films"));
        }
    }
}
