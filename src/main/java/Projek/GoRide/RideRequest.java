package Projek.GoRide;

public class RideRequest {
    private long id;
    private String pickup;
    private String destination;

    public RideRequest(long id, String pickup, String destination) {
        this.id = id;
        this.pickup = pickup;
        this.destination = destination;
    }

    public long getId() {
        return id;
    }

    public String getPickup() {
        return pickup;
    }

    public String getDestination() {
        return destination;
    }
}