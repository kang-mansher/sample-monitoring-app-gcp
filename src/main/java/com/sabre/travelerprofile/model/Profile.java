package com.sabre.travelerprofile.model;

/**
 * Model for Profile Object
 */
public class Profile {

    String name;

    String email;

    String phone;

    public Profile(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Getter Method for email
     * @return String(email)
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Getter Method for phone
     * @return String(phone)
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Getter Method for name
     * @return String(name)
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to update name or phone in profile
     * @param name - name
     * @param phone - phone
     */
    public void update(String name, String phone) {
        this.name = name == null ? this.name : name;
        this.phone = phone == null ? this.phone : phone;
    }
}
