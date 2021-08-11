package com.example.training_ex1;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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

    private ContactsViewModel viewModel;

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
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new ViewModelProvider(requireActivity()).get(ContactsViewModel.class);
        SetFragmentAccordingToContact(view);
    }

    /*
     * Set the fragment's view according to the contact that the fragment receives as an argument
     */
    private void SetFragmentAccordingToContact(final View view){
        final Contact contact = this.viewModel.getContact();
        if (contact != null){
            setName(view, contact);
            setPhoneNumberAndMain(view, contact);
            setImage(view, contact);
        }
    }

    /*
     * Set the fragment's first text view to the contact's name
     */
    private void setName(final View view, final Contact contact){
        final String name = contact.getName();
        final TextView nameTextView = view.findViewById(R.id.textView);
        nameTextView.setText(name);
    }

    /*
     * Set the fragment's second and third text view to the contact's mail and phone number.
     * In case the contact has a mail, the second text view display the mail and the third display
     * the phone number. Otherwise, the second text view display the phone number.
     */
    private void setPhoneNumberAndMain(final View view, final Contact contact){
        final String mail = contact.getMail();
        final String phoneNumber = contact.getPhoneNumber();
        final TextView secondTextView = view.findViewById(R.id.textView2);
        final TextView thirdTextView = view.findViewById(R.id.textView3);
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
    private void setImage(final View view, final Contact contact){
        final Bitmap image = contact.getImage();
        final ImageView imageView = view.findViewById(R.id.imageView);
        if (image != null) { // else set to default photo
            imageView.setImageBitmap(image);
        }
    }
}