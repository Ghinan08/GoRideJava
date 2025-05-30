package Projek.GoRide;

public class Payment implements PaymentMethod{
    private String paymentId;
    private PaymentMethod payMethod;
    private String status;

    public Payment(String paymentId, PaymentMethod payMethod, String status) {
        this.paymentId = paymentId;
        this.payMethod = payMethod;
        this.status = status;
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
        System.out.println("Melakukan pembayaran sebesar: Rp" + amount);
        this.status = "PAID";
    }

    public void setPaymentMethod(PaymentMethod payMethod) {
        this.payMethod = payMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return payMethod;
    }
}

