package Projek.GoRide;

public class Payment implements PaymentMethod {
    private String paymentId;
    private String status;

    public Payment(String paymentId) {
        this.paymentId = paymentId;
        this.status = "PENDING";
    }

    @Override
    public String getPaymentId() {
        return paymentId;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void makePayment(double amount) {
        this.status = "PAID";
    }
}