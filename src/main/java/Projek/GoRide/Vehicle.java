package Projek.GoRide;

public class Vehicle {
    private String plateNumber;
    private String model;
    private String type;

    public Vehicle(String plateNumber, String model, String type) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.type = type;
    }

    public void assignToDriver(int driverId) {
    	
    }

    public void updateInfo(String model, String type) {
        this.model = model;
        this.type = type;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }
}