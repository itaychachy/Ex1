package com.example.training_ex1;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A Fragment to display the contact's information on the MainActivity.
 * @author itaychachy
 */
public class ContactFragment extends Fragment {


    /**
     * Instantiate this Fragment and sets it's views according to the current Contact that was pressed.
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be
     *                 attached to. The fragment should not add the view itself, but this can be
     *                  used to generate the LayoutParams of the view. This value may be null.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     *                          saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contact, container, false);
        SetFragmentAccordingToContact(view);
        return view;
    }

    /*
     * Set the fragment's view according to the contact that the fragment receives as an argument
     */
    private void SetFragmentAccordingToContact(View view){
        Contact contact = ContactFragmentArgs.fromBundle(requireArguments()).getContact();
        if (contact != null){
            setName(view, contact);
            setPhoneNumberAndMain(view, contact);
            setImage(view, contact);
        }
    }

    /*
     * Set the fragment's first text view to the contact's name
     */
    private void setName(View view, Contact contact){
        String name = contact.getName();
        TextView nameTextView = view.findViewById(R.id.textView);
        nameTextView.setText(name);
    }

    /*
     * Set the fragment's second and third text view to the contact's mail and phone number.
     * In case the contact has a mail, the second text view display the mail and the third display
     * the phone number. Otherwise, the second text view display the phone number.
     */
    private void setPhoneNumberAndMain(View view, Contact contact){
        String mail = contact.getMail();
        String phoneNumber = contact.getPhoneNumber();
        TextView secondTextView = view.findViewById(R.id.textView2);
        TextView thirdTextView = view.findViewById(R.id.textView3);
        if (mail != null) { // Mail is optional
            secondTextView.setText(mail);
            thirdTextView.setText(phoneNumber);
        } else {
            secondTextView.setText(phoneNumber);
        }
    }

    /*
     * Set the fragment's image view to the contact's image in case the contact has an image.
     * Otherwise, the image view stay with the default fragment's image.
     */
    private void setImage(View view, Contact contact){
        Bitmap image = contact.getImage();
        ImageView imageView = view.findViewById(R.id.imageView);
        if (image != null) { // else set to default photo
            imageView.setImageBitmap(image);
        }
    }
}