package Model;

import java.util.ArrayList;

public class Customer {
    private final int customerId;
    private ArrayList<Booking> bookings;
    private String password;

    public Customer(int customerId, String password) {
        this.customerId = customerId;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
}
