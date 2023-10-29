package com.example.motogp;

public class Driver {

    private int driverID;
    private String firstName;
    private String lastName;
    private String Country;
    private String image;

    public Driver(int driverID, String firstName, String lastName, String country, String image) {
        this.driverID = driverID;
        this.firstName = firstName;
        this.lastName = lastName;
        Country = country;
        this.image = image;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
