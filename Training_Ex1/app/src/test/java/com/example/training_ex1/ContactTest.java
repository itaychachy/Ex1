package com.example.training_ex1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static com.google.common.truth.Truth.*;

import android.graphics.Bitmap;

/**
 * Simple unit test for the Contact class
 * @author itaychachy
 */
public class ContactTest {

    @Mock
    private Bitmap image;

    private Contact contact;

    @Before
    public void setUp(){
        contact = new Contact("name", "123456");
        this.image = Mockito.mock(Bitmap.class);
    }

    @Test
    public void testInitializeContact() {
        assertThat(contact.getName()).isEqualTo("name");
        assertThat(contact.getPhoneNumber()).isEqualTo("123456");
        assertThat(contact.getMail()).isNull();
        assertThat(contact.getImage()).isNull();
    }

    @Test
    public void testSetImage(){
        assertThat(contact.getImage()).isNull();

        Mockito.when(image.getHeight()).thenReturn(123);
        contact.setImage(image);

        assertThat(contact.getImage()).isNotNull();
        assertThat(contact.getImage()).isEqualTo(image);
        assertThat(contact.getImage().getHeight()).isEqualTo(123);

        contact.setImage(null);
        assertThat(contact.getImage()).isNull();
    }

    @Test
    public void testSetMail(){
        assertThat(contact.getMail()).isNull();

        String mail = "employee@lightricks.com";
        contact.setMail(mail);

        assertThat(contact.getMail()).isNotNull();
        assertThat(contact.getMail()).isEqualTo(mail);

        mail = "no_mail";
        assertThat(contact.getMail()).isNotEqualTo(mail);

        contact.setMail(null);
        assertThat(contact.getMail()).isNull();
    }

}