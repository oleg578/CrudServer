package be.hyf.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FilmTest {

    @Test
    void shouldSetAndGetSpecialFeatures() {
        Film film = new Film();
        String[] features = new String[]{"Trailers", "Commentaries", "Deleted Scenes"};
        film.setSpecialFeatures(features);

        assertArrayEquals(features, film.getSpecialFeatures());
    }

    @Test
    void shouldCreateFilmWithAllArgsConstructor() {
        String[] features = new String[]{"Trailers", "Behind the Scenes"};
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Film film = new Film(
                1,
                "ACADEMY DINOSAUR",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                2006,
                1,
                0,
                6,
                new BigDecimal("0.99"),
                86,
                new BigDecimal("20.99"),
                MPARating.PG,
                features,
                now
        );

        assertEquals(1, film.getId());
        assertEquals("ACADEMY DINOSAUR", film.getTitle());
        assertEquals(MPARating.PG, film.getRating());
        assertArrayEquals(features, film.getSpecialFeatures());
        assertEquals(now, film.getLast_update());
    }

    @Test
    void shouldHandleNullSpecialFeatures() {
        Film film = new Film();
        film.setSpecialFeatures(null);

        assertNull(film.getSpecialFeatures());
    }
}
