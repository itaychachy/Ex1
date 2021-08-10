package com.example.training_ex1;

import android.graphics.Bitmap;
import org.jetbrains.annotations.Nullable;
import java.io.Serializable;

/**
 * A class for contact's information. Serializable.
 * @author itaychachy
 */
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final String phoneNumber;
    private String mail;
    private Bitmap image;

    /**
     * Contact constructor. Sets the mandatory name and phoneNumber fields.
     * @param name the contact's name
     * @param phoneNumber - the contact's phone number
     */
    public Contact(final String name, final String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = null;
        this.image = null;
    }

    /**
     * Sets an image profile to the Contact
     * @param image contact's photo
     */
    public void setImage(Bitmap image) {
        this.image = image;
    }

    /**
     * Sets a mail Address to the Contact
     * @param mailAddress contact's mail address
     */
    public void setMail(String mailAddress) {
        this.mail = mailAddress;
    }

    /**
     * @return contact's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return contact's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * @return contact's mail
     */
    @Nullable
    public String getMail() {
        return mail;
    }

    /**
     * @return contact's photo
     */
    @Nullable
    public Bitmap getImage() {
        return image;
    }

}
