package Projek.GoRide;

public interface PaymentMethod {
    String getPaymentId();
    String getStatus();
    void makePayment(double amount);
}