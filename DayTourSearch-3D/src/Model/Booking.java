package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
    private final int customerId;
    private final int dayTripId;
    private int numberOfGuests;
    private String title;
    private int amount;
    private int duration;
    private double rating;
    private LocalDate date;
    private LocalTime startTime;
    private String language;
    private String location;
    private String activity;
    private String description;
    private String review;



    public Booking(int customerId, int dayTripId, int numberOfGuests, String title, int amount,
                   int duration, LocalDate date, LocalTime startTime, String language,
                   String location, String activity, String description) {
        this.customerId = customerId;
        this.dayTripId = dayTripId;
        this.numberOfGuests = numberOfGuests;
        this.title = title;
        this.amount = amount;
        this.duration = duration;
        this.date = date;
        this.startTime = startTime;
        this.language = language;
        this.location = location;
        this.activity = activity;
        this.description = description;
        this.review = "";
        this.rating = -1;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getDayTripId() {
        return dayTripId;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
