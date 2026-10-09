package model;

public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(int userId, String name, String email, String password) {
        super(userId, name, email, password, "ADMIN");
    }

    public void manageUsers() {
        System.out.println("Admin can manage users.");
    }
}