package Projek.GoRide;

public abstract class Person {
    private int id;
    private String name;
    private long phone;
    private String email;

    public Person(int id, String name, long phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public abstract String getRole();
}