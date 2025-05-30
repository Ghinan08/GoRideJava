package Projek.GoRide;

public class Driver extends Person {
    private String status;
    private String plateNumber;
    private String model;
    private String type;

    public Driver(int id, String name, int phone, String email, String plateNumber, String model, String type) {
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
    public String getplateNumber() {
        return plateNumber;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String toString(){
        return "- Plat Nomor: " + this.plateNumber +
            "\n- Model: " + this.model +
            "\n- Type: " + this.type;
    }
}
