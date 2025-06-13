package Projek.GoRide;

public class Invoice {
    private int id;
    private int paymentId;
    private String details;
    private int date;

    public Invoice(int id, int paymentId, String details, int date) {
        this.id = id;
        this.paymentId = paymentId;
        this.details = details;
        this.date = date;
    }

    public int getId() {
        return id;
    }
}