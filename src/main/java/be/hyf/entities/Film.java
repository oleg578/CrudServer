package be.hyf.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Film {
    @JsonProperty("film_id")
    private int id;

    private String title;
    private String description;
    @JsonProperty("release_year")
    private int releaseYear;
    @JsonProperty("language_id")
    private int languageId;
    @JsonProperty("original_language_id")
    private Integer originalLanguageId; // nullable in the DB
    @JsonProperty("rental_duration")
    private int rentalDuration;
    @JsonProperty("rental_rate")
    private BigDecimal rentalRate;
    private int length;
    @JsonProperty("replacement_cost")
    private BigDecimal replacementCost;
    private MPARating rating;
    @JsonProperty("special_features")
    private String[] specialFeatures;

    @JsonProperty("last_update")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp last_update;

    public Film() {}

    public Film(int id, String title, String description, int releaseYear, int languageId,
                Integer originalLanguageId, int rentalDuration, BigDecimal rentalRate, int length,
                BigDecimal replacementCost, MPARating rating, String[] specialFeatures,
                Timestamp last_update) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.originalLanguageId = originalLanguageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.last_update = last_update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public Integer getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(Integer originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public MPARating getRating() {
        return rating;
    }

    public void setRating(MPARating rating) {
        this.rating = rating;
    }

    public String[] getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String[] specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }
}
