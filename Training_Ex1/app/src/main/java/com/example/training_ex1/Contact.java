package com.example.training_ex1;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * A class for contact's information
 */
public class Contact implements Serializable {

    // Data
    private final String name;
    private final String phoneNumber;
    private String mail;
    private Bitmap photo;

    /**
     * Contact constructor, without mail parameter
     * @param name the contact's name
     * @param phoneNumber - the contact's phone number
     */
    public Contact(final String name, final String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = null;
        this.photo = null;
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

    /**
     * @return contact's photo
     */
    public Bitmap getPhoto() {
        return photo;
    }

    /**
     * Sets an image profile to the Contact
     * @param photo contact's photo
     */
    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    /**
     * Sets a mail Address to the Contact
     * @param mailAddress contact's mail address
     */
    public void setMail(String mailAddress) {
        this.mail = mailAddress;
    }

}
