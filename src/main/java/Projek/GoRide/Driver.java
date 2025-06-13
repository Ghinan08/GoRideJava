package Projek.GoRide;

public class Driver extends Person {
    private String plateNumber;
    private String model;
    private String type;

    public Driver(int id, String name, long phone, String email, String plateNumber, String model, String type) {
        super(id, name, phone, email);
        this.plateNumber = plateNumber;
        this.model = model;
        this.type = type;
    }

    @Override
    public String getRole() {
        return "Driver";
    }
    
    public String getplateNumber() {
        return plateNumber;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }
}