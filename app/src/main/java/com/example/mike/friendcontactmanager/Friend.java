package com.example.mike.friendcontactmanager;

/**
 * Created by Mike on 8/2/2017.
 */

public class Friend {

    private int id;
    private String email;
    private String firstName;
    private String lastName;

    public Friend(int id,String email, String firstName, String lastName) {

        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }


    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String newLastName) {
        lastName = newLastName;
    }

    public String toString() {
        return id + " " + firstName + " " + lastName + " " + email;
    }
}