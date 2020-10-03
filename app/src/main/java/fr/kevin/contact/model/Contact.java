package fr.kevin.contact.model;

import java.io.Serializable;

public class Contact implements Serializable {

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

    public Contact(String firstName, String lastName) {
        this(firstName, lastName, "06 23 37 74 84", "04 79 23 35 05", firstName + "." + lastName + "@etu.univ-smb.fr", "Bâtiment 10, 73376 Le Bourget-du-Lac");
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
