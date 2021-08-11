package com.example.training_ex1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A Fragment to display home screen of the App. It allows the user to proceed to contact list in case
 * the user granted his permission to access them.
 * @author itaychachy
 */
public class EntryFragment extends Fragment {


    /**
     * Instantiate this Fragment and sets it's button view.
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
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_entry, container, false);
        setButton(view);
        return view;
    }

    /*
     * Set the button onClickListener in case of a click
     */
    private void setButton(final View view){
        final Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                handlePermission();
            }
        });
    }

    /*
     * In  case the button was pressed, it will check if a permission was granted to access the user's
     * contact list. If not, it will ask for it, otherwise it will navigate to the ContactsMenuFragment
     */
    private void handlePermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            requireActivity().getApplicationContext().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                requireActivity().requestPermissions(new String[] {Manifest.permission.READ_CONTACTS}, MainActivity.CONTACTS_REQUEST_CODE);
        }
        else {
            navigateToContactsMenuFragment();
        }
    }

    /*
     * Navigates to the ContactsMenuFragment
     */
    private void navigateToContactsMenuFragment() {
        final NavDirections action = EntryFragmentDirections.actionEntryFragmentToContactMenuFragment();
        Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment)).navigate(action);
    }
}