package com.example.training_ex1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts_menu, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Observer<ArrayList<Contact>> observer = contacts -> setRecyclerView(view, contacts);
        this.viewModel = new ViewModelProvider(requireActivity(), new ViewModelFactory(new ContactsRepository())).get(ContactsViewModel.class);
        viewModel.observeContacts().observe(getViewLifecycleOwner(), observer);
        viewModel.LoadingMenu(requireContext());
    }

    /*
     * Set the fragment's recycler view to display the device's contacts
     */
    private void setRecyclerView(final View view, final ArrayList<Contact> contacts){
        // set up the RecyclerView
        final RecyclerView recyclerView = view.findViewById(R.id.rvContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new RecyclerViewAdapter(view.getContext(), contacts);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    public void onContactClick(final View view, final Contact contact) {
        this.viewModel.contactClicked(contact);
        final NavDirections action = ContactsMenuFragmentDirections.actionContactMenuFragmentToContactFragment().setContact(contact);
        Navigation.findNavController(view).navigate(action);
    }
}