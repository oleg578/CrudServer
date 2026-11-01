package be.hyf.entities;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Language {
    private int id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp last_update;

    // for deserialization
    public Language() {
    }

    public Language(int id, String name, Timestamp last_update) {
        this.id = id;
        this.name = name;
        this.last_update = last_update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "Language [id=" + id + ", name=" + name + ", last_update=" + last_update + "]";
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper obm = new ObjectMapper();
        return obm.writeValueAsString(this);
    }

    public static Language fromJSON(String json) throws JsonProcessingException {
        ObjectMapper obm = new ObjectMapper();
        return obm.readValue(json, Language.class);
    }

}
