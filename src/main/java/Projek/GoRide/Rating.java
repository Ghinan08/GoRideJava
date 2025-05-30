package Projek.GoRide;

public class Rating {
    private int driverId;
    private boolean isAvailable;
    private String location;
    private int ratingValue;
    private String comment;

    public Rating(int driverId, boolean isAvailable, String location) {
        this.driverId = driverId;
        this.isAvailable = isAvailable;
        this.location = location;
    }

    public void submit(int rating, String comment) {
        this.ratingValue = rating;
        this.comment = comment;
    }

    public void edit(int newRating, String newComment) {
        this.ratingValue = newRating;
        this.comment = newComment;
    } 

    public int getDriverId() {
        return driverId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getLocation() {
        return location;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public String getComment() {
        return comment;
    }
}