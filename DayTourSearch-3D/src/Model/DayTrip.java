package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class DayTrip {
    private final int dayTripId;
    private int price;
    private int duration;
    private LocalDate date;
    private LocalTime startTime;
    private int availableSeats;
    private String language;
    private char location;
    private String activity;
    private String description;
    private LocalDate dateAdded;

    public DayTrip(int dayTripId, int price, int duration, LocalDate date, LocalTime startTime, int availableSeats, String language, char location, String activity) {
        this.dayTripId = dayTripId;
        this.price = price;
        this.duration = duration;
        this.date = date;
        this.startTime = startTime;
        this.availableSeats = availableSeats;
        this.language = language;
        this.location = location;
        this.activity = activity;
        this.dateAdded = LocalDate.now();
    }

    public int getDayTripId() {
        return dayTripId;
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

    public char getLocation() {
        return location;
    }

    public void setLocation(char location) {
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
