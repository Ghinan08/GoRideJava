package Projek.GoRide;

public class Driver extends Person {
    private String status;
    private String plateNumber;
    private String model;
    private String type;

    public Driver(Long id, String name, int phone, String email, String plateNumber, String model, String type) {
        super(id, name, phone, email);
        this.plateNumber = plateNumber;
        this.model = model;
        this.type = type;
        this.status = "Offline";
    }

    public void acceptOrder() {
        System.out.println("Order accepted.");
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    @Override
    public String getRole() {
        return "Driver";
    }
}
