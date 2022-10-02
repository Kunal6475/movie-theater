package com.jpmc.theater;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({ "sequenceOfTheDay", "startTime", "movie"})  // To keep the json field order of seq Of Day, start Time and then movie attributes
public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    @JsonIgnore
    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    // Used for returning start time in human-readable format in json field
    @JsonProperty("startTime")
    public String getStartTimeValueInReadableFormat(){
        return showStartTime.toString();
    }

    @JsonIgnore
    public double getMovieFee() {
        return movie.calculateTicketPrice(this);
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

}
