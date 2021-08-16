package com.example.training_ex1;

import android.graphics.Bitmap;
import java.io.Serializable;

/**
 * A class for contact's information. Serializable.
 * @author itaychachy
 */
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final String phoneNumber;
    private final String mail;
    private final Bitmap image;


    public Contact(final String name, final String phoneNumber, final String mail, final Bitmap image){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public Bitmap getImage() {
        return image;
    }

}
