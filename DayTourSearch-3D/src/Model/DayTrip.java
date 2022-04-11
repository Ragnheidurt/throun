package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class DayTrip {
    private final int dayTripId;
    private String title;
    private int price;
    private int duration;
    private double rating;
    private LocalDate date;
    private LocalTime startTime;
    private int availableSeats;
    private String language;
    private String location;
    private String activity;
    private String description;
    private LocalDate dateAdded;

    public DayTrip(int dayTripId, String title, int price, int duration, LocalDate date,
                   LocalTime startTime, int availableSeats, String language, String location,
                   String activity, LocalDate dateAdded, String description) {
        this.dayTripId = dayTripId;
        this.title = title;
        this.price = price;
        this.duration = duration;
        this.date = date;
        this.startTime = startTime;
        this.availableSeats = availableSeats;
        this.language = language;
        this.location = location;
        this.activity = activity;
        this.dateAdded = dateAdded;
        this.description = description;
        this.rating = -1.0;
    }

    public int getDayTripId() {
        return dayTripId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setRating(double rating) { this.rating = rating;}

    public double getRating() { return rating;}

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

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
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

    public LocalDate getDateAdded() {
        return dateAdded;
    }
}
