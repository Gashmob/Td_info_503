package fr.kevin.contact.model;

public class Contact {
    private static int ID = 1;

    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String home;
    private String mail;
    private String location;

    public Contact(String firstName, String lastName, String phone, String home, String mail, String location) {
        this.id = ID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.home = home;
        this.mail = mail;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getHome() {
        return home;
    }

    public String getMail() {
        return mail;
    }

    public String getLocation() {
        return location;
    }
}
