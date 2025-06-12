package Projek.GoRide;

public class DriverStatus {
    public static final String AVAILABLE = null;
	public static final String BOOKED = null;
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
