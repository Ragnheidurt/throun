package Model;

public class Booking {
    private final int customerId;
    private final int dayTripId;
    private int numberOfGuests;

    public Booking(int customerId, int dayTripId, int numberOfGuests) {
        this.customerId = customerId;
        this.dayTripId = dayTripId;
        this.numberOfGuests = numberOfGuests;
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
