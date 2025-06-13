package Projek.GoRide;

public class Location {
    private double latitude;
    private double longitude;
    private String address;

    public Location(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}