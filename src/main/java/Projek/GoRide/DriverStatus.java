package Projek.GoRide;

public class DriverStatus {
    private int driverId;
    private boolean isAvailable;
    private String location;

    public DriverStatus(int driverId, boolean isAvailable, String location) {
        this.driverId = driverId;
        this.isAvailable = isAvailable;
        this.location = location;
    }

    public void goOnline() {
        this.isAvailable = true;
    }

    public void goOffline() {
        this.isAvailable = false;
    }

    public void updateLocation(String newLocation) {
        this.location = newLocation;
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
}