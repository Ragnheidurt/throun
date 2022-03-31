package Model;

public class Review {
    private int rating;
    private String review;
    private final int customerId;
    private final int dayTripId;

    public Review(int rating, String review, int customerId, int dayTripId) {
        this.rating = rating;
        this.review = review;
        this.customerId = customerId;
        this.dayTripId = dayTripId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getDayTripId() {
        return dayTripId;
    }

}
