package Projek.GoRide;

public class Rating {
    private int driverId;
    private int ratingValue;
    private String comment;

    public Rating(int driverId) {
        this.driverId = driverId;
    }

    public void submit(int rating, String comment) {
        this.ratingValue = rating;
        this.comment = comment;
    }

    public int getDriverId() {
        return driverId;
    }
}