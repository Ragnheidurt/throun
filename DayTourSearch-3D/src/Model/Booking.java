package Model;

public class Booking {
    private int customerId;
    private int dayTripId;
    private int numberOfGuests;

    public Booking(int customerId, int dayTripId, int numberOfGuests) {
        this.customerId = customerId;
        this.dayTripId = dayTripId;
        this.numberOfGuests = numberOfGuests;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDayTripId() {
        return dayTripId;
    }

    public void setDayTripId(int dayTripId) {
        this.dayTripId = dayTripId;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
