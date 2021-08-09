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
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Contact contact = ContactFragmentArgs.fromBundle(requireArguments()).getContact();
        final View view = inflater.inflate(R.layout.fragment_contact, container, false);
        if (contact != null){
            String name = contact.getName();
            String mail = contact.getMail();
            String phone = contact.getPhoneNumber();
            Bitmap photo = contact.getImage();

            TextView firstTextView = view.findViewById(R.id.textView);
            TextView secondTextView = view.findViewById(R.id.textView2);
            TextView thirdTextView = view.findViewById(R.id.textView3);
            ImageView imageView = view.findViewById(R.id.imageView);

            firstTextView.setText(name);
            if (mail != null) { // Mail is optional
                secondTextView.setText(mail);
                thirdTextView.setText(phone);
            } else {
                secondTextView.setText(phone);
            }
            if (photo != null) { // else set to default photo
                imageView.setImageBitmap(photo);
            }
        }
        // Inflate the layout for this fragment
        return view;
    }
}