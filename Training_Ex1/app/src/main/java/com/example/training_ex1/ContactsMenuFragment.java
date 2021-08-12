package com.example.training_ex1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * A Fragment to display the contact menu on the MainActivity.
 * Implements ItemClickListener
 * @see RecyclerViewAdapter.ItemClickListener
 * @author itaychachy
 */
public class ContactsMenuFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener{

    private RecyclerViewAdapter adapter;
    private ContactsViewModel viewModel;

    /**
     * Instantiate this Fragment and sets it's recycler view according to the contacts on the device.
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be
     *                 attached to. The fragment should not add the view itself, but this can be
     *                  used to generate the LayoutParams of the view. This value may be null.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     *                          saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts_menu, container, false);
    }

    /**
     * Called after onCreateView. Sets the ViewModel.
     * @param view The View returned by onCreateView
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     *                           saved state as given here. This value may be null.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = (ContactsViewModel) new ViewModelProvider(requireActivity(), new ViewModelFactory(new ContactsRepository())).get(ContactsViewModel.class);
        setRecyclerView(view);
    }

    /*
     * Set the fragment's recycler view to display the device's contacts
     */
    private void setRecyclerView(final View view){
        final ArrayList<Contact> contacts = viewModel.getContactsList();
        // set up the RecyclerView
        final RecyclerView recyclerView = view.findViewById(R.id.rvContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new RecyclerViewAdapter(view.getContext(), contacts);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * In case a contact was pressed, the fragment alert the ViewModel about this event and
     * navigates to ContactFragment and sends it the contact's object
     * @param view current view
     * @param position the position of the contact that wea pressed
     */
    @Override
    public void onContactClick(View view, int position) {
        final Contact contact = adapter.getItem(position);
        this.viewModel.contactClicked(contact);
        final NavDirections action = ContactsMenuFragmentDirections.actionContactMenuFragmentToContactFragment().setContact(contact);
        Navigation.findNavController(view).navigate(action);
    }
}