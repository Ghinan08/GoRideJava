package Projek.GoRide;

import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    private List<Order> orderHistory = new ArrayList<>();

    public User(int id, String name, long phone, String email) {
        super(id, name, phone, email);
    }

    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    @Override
    public String getRole() {
        return "User";
    }
}