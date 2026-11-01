package be.hyf.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

class LanguageTest {

    private static final Timestamp FIXED_TIMESTAMP = new Timestamp(1_700_000_000_000L);

    @Test
    void shouldExposeAllValuesWhenCreatedWithConstructor() {
        Language language = new Language(1, "English", FIXED_TIMESTAMP);

        assertEquals(1, language.getId());
        assertEquals("English", language.getName());
        assertEquals(FIXED_TIMESTAMP, language.getLast_update());
    }

    @Test
    void shouldHaveDefaultValuesWhenCreatedWithNoArgsConstructor() {
        Language language = new Language();

        assertEquals(0, language.getId());
        assertNull(language.getName());
        assertNull(language.getLast_update());
    }

    @Test
    void shouldReturnNewValuesWhenSettersAreCalled() {
        Language language = new Language();

        language.setId(7);
        language.setName("French");
        language.setLast_update(FIXED_TIMESTAMP);

        assertEquals(7, language.getId());
        assertEquals("French", language.getName());
        assertEquals(FIXED_TIMESTAMP, language.getLast_update());
    }

    @Test
    void shouldContainAllFieldsWhenToStringIsCalled() {
        Language language = new Language(2, "German", FIXED_TIMESTAMP);

        assertEquals("Language [id=2, name=German, last_update=" + FIXED_TIMESTAMP + "]", language.toString());
    }

    @Test
    void shouldSerializeAllFieldsWhenToJsonIsCalled() throws JsonProcessingException {
        Language language = new Language(3, "Italian", FIXED_TIMESTAMP);

        JsonNode node = new ObjectMapper().readTree(language.toJSON());

        assertEquals(3, node.get("id").asInt());
        assertEquals("Italian", node.get("name").asText());
        assertEquals(FIXED_TIMESTAMP.getTime(), node.get("last_update").asLong());
    }

    @Test
    void shouldSerializeNullFieldsWhenValuesAreMissing() throws JsonProcessingException {
        JsonNode node = new ObjectMapper().readTree(new Language().toJSON());

        assertEquals(0, node.get("id").asInt());
        assertTrue(node.get("name").isNull());
        assertTrue(node.get("last_update").isNull());
    }

    @Test
    void shouldRestoreSameValuesWhenSerializedAndDeserialized() throws JsonProcessingException {
        Language original = new Language(4, "Spanish", FIXED_TIMESTAMP);

        Language restored = Language.fromJSON(original.toJSON());

        assertEquals(original.getId(), restored.getId());
        assertEquals(original.getName(), restored.getName());
        assertEquals(original.getLast_update(), restored.getLast_update());
    }

    @Test
    void shouldParseFieldsWhenFromJsonReceivesValidJson() throws JsonProcessingException {
        String json = "{\"id\":5,\"name\":\"Japanese\",\"last_update\":1700000000000}";

        Language language = Language.fromJSON(json);

        assertEquals(5, language.getId());
        assertEquals("Japanese", language.getName());
        assertEquals(FIXED_TIMESTAMP, language.getLast_update());
    }

    @Test
    void shouldLeaveMissingFieldsAtDefaultsWhenJsonIsPartial() throws JsonProcessingException {
        Language language = Language.fromJSON("{\"name\":\"Latin\"}");

        assertEquals(0, language.getId());
        assertEquals("Latin", language.getName());
        assertNull(language.getLast_update());
    }

    @Test
    void shouldThrowWhenFromJsonReceivesMalformedJson() {
        assertThrows(JsonProcessingException.class, () -> Language.fromJSON("{not json"));
    }

    @Test
    void shouldThrowWhenFromJsonReceivesUnknownProperty() {
        assertThrows(JsonProcessingException.class,
                () -> Language.fromJSON("{\"id\":1,\"name\":\"X\",\"unknown\":true}"));
    }

    @Test
    void shouldThrowWhenFromJsonReceivesWrongFieldType() {
        assertThrows(JsonProcessingException.class, () -> Language.fromJSON("{\"id\":\"abc\"}"));
    }
}
