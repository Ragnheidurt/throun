package Model;

import java.util.ArrayList;

public class Customer {
    private final int customerId;
    private ArrayList<Booking> bookings = new ArrayList<Booking>();
    private String password;
    private String username;

    public Customer(int customerId, String password,String username) {
        this.customerId = customerId;
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void addBooking(Booking booking){
        bookings.add(booking);
    }
}
