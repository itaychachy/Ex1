package com.example.training_ex1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
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


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_entry, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setButton(view);
    }

    /*
     * Set the button onClickListener in case of a click
     */
    private void setButton(final View view){
        final Button button = view.findViewById(R.id.button);
        button.setOnClickListener(b -> handlePermission());
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