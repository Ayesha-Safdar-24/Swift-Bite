package model;

public class Customer extends User {

    public Customer(String username, String phone, String password) {
        super(username, phone, password);
    }

    @Override
    public String getRole() {
        return "Customer";
    }
}