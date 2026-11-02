package be.hyf.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MPARating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String value;

    MPARating(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MPARating fromValue(String value) {
        for (MPARating rating : MPARating.values()) {
            if (rating.value.equalsIgnoreCase(value)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Unknown rating: " + value);
    }
}
