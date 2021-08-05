package com.example.training_ex1;

import java.io.Serializable;

/**
 * A class for each contact's information
 */
public class Contact implements Serializable {

    // Data
    private final String name;
    private final String mail;
    private final String phoneNumber;

    /**
     * Contact constructor
     * @param name the contact's name
     * @param mail - the contact's mail
     * @param phoneNumber - the contact's phone number
     */
    public Contact(final String name, final String mail, final String phoneNumber){
        this.name = name;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Contact constructor, without mail parameter
     * @param name the contact's name
     * @param phoneNumber - the contact's phone number
     */
    public Contact(final String name, final String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = null;
    }

    /**
     * @return contact's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return contact's mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @return contact's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
