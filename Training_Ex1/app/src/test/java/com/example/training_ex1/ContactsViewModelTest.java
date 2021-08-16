package com.example.training_ex1;

import static com.google.common.truth.Truth.*;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;

/**
 * Unit test for the ContactsViewModel
 * @author itaychachy
 */
@RunWith(AndroidJUnit4.class)
public class ContactsViewModelTest {

    @Mock
    private ContactsRepository mockRepository;

    @Mock
    private Contact mockContact;

    private ContactsViewModel contactsViewModel;

    @Before
    public void setUp(){
        this.mockContact = Mockito.mock(Contact.class);
        this.mockRepository = Mockito.mock(ContactsRepository.class);
        this.contactsViewModel = new ContactsViewModel(this.mockRepository);
    }

//    @Test
//    public void testGetContactsList_equalToRepositoryContactsList(){
//        ArrayList<Contact> contacts = new ArrayList<Contact>() {
//            {
//                add(new Contact("test", "1", null, null));
//                add(new Contact("test", "2", null, null));
//                add(new Contact("test", "3", null, null));
//            }
//        };
//
//        Mockito.when(mockRepository.getContactsList()).thenReturn(contacts);
//
//        assertThat(contactsViewModel.getContactsList()).isEqualTo(contacts);
//    }

//    @Test
//    public void testContactClicked_returnsNull(){
//        assertThat(contactsViewModel.clickedContact.getValue()).isNull();
//    }

//    @Test
//    public void testContactClicked_updatesClickedContactField(){
//        Mockito.when(mockContact.getName()).thenReturn("Mock");
//
//        contactsViewModel.contactClicked(mockContact);
//
//        assertThat(contactsViewModel.clickedContact.getValue().getName()).isEqualTo("Mock");
//    }

//    @Test
//    public void testMultipleContactClicked_shouldChangeClickedContactField(){
//        Contact c1 = new Contact("test", "1", null, null);
//        Contact c2 = new Contact("test", "2", null, null);
//        Contact c3 = new Contact("test", "3", null, null);
//
//        contactsViewModel.contactClicked(c1);
//        contactsViewModel.contactClicked(c2);
//
//        assertThat(contactsViewModel.clickedContact.getValue()).isNotEqualTo(c1);
//
//        contactsViewModel.contactClicked(c3);
//
//        assertThat(contactsViewModel.clickedContact.getValue().getPhoneNumber()).isEqualTo(c3.getPhoneNumber());
//    }
}