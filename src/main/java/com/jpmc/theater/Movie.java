package com.jpmc.theater;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonPropertyOrder({ "title", "duration", "ticketPrice"})  // To keep the json field order of title, duration, ticket price
public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;
    private static int SHOW_TIME_DISCOUNT_START_HOUR = 11;
    private static int SHOW_TIME_DISCOUNT_END_HOUR = 16;

    private String title;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    @JsonIgnore
    public Duration getRunningTime() {
        return runningTime;
    }

    @JsonProperty("duration")
    public String getDuration(){
        return Utils.humanReadableFormat(runningTime);
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay(), showing.getStartTime());
    }

    private double getDiscount(int showSequence, LocalDateTime showStartTime) {
        double specialDiscount = getMovieCodeSpecialDiscount();
        double sequenceDiscount = getSequenceDiscount(showSequence);
        double showStartTimeDiscount = getShowStartTimingDiscount(showStartTime);

        // biggest discount wins
        return Math.max(Math.max(specialDiscount, sequenceDiscount), showStartTimeDiscount);
    }

    /***
     * Method to calculate discount associated with the movie code
     * @return discount calculated for the movie code
     */
    private double getMovieCodeSpecialDiscount(){
        double specialDiscount = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }
        return specialDiscount;
    }

    /***
     * Method to calculate show start time discount
     * @param showStartTime : start time of the movie
     * @return discount for the movie start time
     */
    private double getShowStartTimingDiscount(LocalDateTime showStartTime){
        double showStartTimeDiscount = 0;
        if (showStartTime.getHour()>=SHOW_TIME_DISCOUNT_START_HOUR && showStartTime.getHour()<=SHOW_TIME_DISCOUNT_END_HOUR){
            showStartTimeDiscount = ticketPrice * 0.25;     // 25% discount for movies starting between 11 AM to 4 PM
        }
        return showStartTimeDiscount;
    }

    /***
     * Method to calculate sequence discount
     * @param showSequence : The sequence number of the show
     * @return discount for the show sequence
     */
    private double getSequenceDiscount(int showSequence){
        double sequenceDiscount;
        switch (showSequence){
            case 1:
                sequenceDiscount = 3; // $3 discount for 1st show
                break;
            case 2:
                sequenceDiscount = 2; // $2 discount for 2nd show
                break;
            case 7:
                sequenceDiscount = 1; // $1 discount for 7th show
                break;
            default:
                sequenceDiscount = 0;
        }
        return sequenceDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, specialCode);
    }
}