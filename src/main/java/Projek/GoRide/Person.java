package Projek.GoRide;

public abstract class Person {
    private Long id;
    private String name;
    private int phone;
    private String email;

    public Person(Long id, String name, int phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public abstract String getRole();
}

