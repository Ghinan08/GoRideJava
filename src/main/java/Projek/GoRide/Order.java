package Projek.GoRide;

public class Order implements RideAction {
    private int id;
    private int userId;
    private int driverId;
    private String status;
    private double fare;
    private Route route;

    public Order(int id, int userId, int driverId, double fare, Route route) {
        this.id = id;
        this.userId = userId;
        this.driverId = driverId;
        this.status = "Menunggu Driver";
        this.fare = fare;
        this.route = route;
    }

    public void complete() {
        this.status = "Completed";
    }

    public void cancel() {
        this.status = "Cancelled";
    }

    @Override
    public void startRide() {
        this.status = "In Progress";
    }

    @Override
    public void endRide() {
        this.status = "Completed";
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getStatus() {
        return status;
    }

    public double getFare() {
        return fare;
    }

    public Route getRoute() {
        return route;
    }
}