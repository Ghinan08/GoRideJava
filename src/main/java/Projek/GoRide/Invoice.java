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

    public void generate() {

    }

    public void sendToUser() {

    }

    public int getId() {
        return id;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public String getDetails() {
        return details;
    }

    public int getDate() {
        return date;
    }
}