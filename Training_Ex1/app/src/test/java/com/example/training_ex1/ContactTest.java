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
        contact = new Contact("name", "123456", "employee@lightricks.com", image);
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

        Mockito.when(image.getHeight()).thenReturn(123);

        assertThat(contact.getImage()).isNotNull();
        assertThat(contact.getImage()).isEqualTo(image);
        assertThat(contact.getImage().getHeight()).isEqualTo(123);

    }

    @Test
    public void testSetMail(){

        String mail = "employee@lightricks.com";
        assertThat(contact.getMail()).isNotNull();
        assertThat(contact.getMail()).isEqualTo(mail);

        mail = "no_mail";
        assertThat(contact.getMail()).isNotEqualTo(mail);
    }

}