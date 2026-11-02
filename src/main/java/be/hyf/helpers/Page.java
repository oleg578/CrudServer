package be.hyf.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class Page {
    List<Object> items;
    String previous;
    String next;
    String cursor;

    public Page(List<Object> items, String previous, String next, String cursor) {
        this.items = items;
        this.previous = previous;
        this.next = next;
        this.cursor = cursor;
    }

    public Page() {}

    String toJSON() throws JsonProcessingException {
        return Json.MAPPER.writeValueAsString(this);
    }
}
