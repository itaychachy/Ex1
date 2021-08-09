package com.example.training_ex1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class EntryFragment extends Fragment {

    private static final int CONTACTS_REQUEST_CODE = 1;
    private static final String PERMISSION_GRANTED = "Permission to contacts granted";
    private static final String PERMISSION_DENIED = "Permission to contacts denied.";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entry, container, false);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlePermission(view);
            }
        });
        return view;
    }

    public void handlePermission(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (requireActivity().getApplicationContext().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                requireActivity().requestPermissions(new String[] {Manifest.permission.READ_CONTACTS}, CONTACTS_REQUEST_CODE);
            }
        }
        else {
            enterContactsList();
        }
    }

    private void enterContactsList() {
        NavDirections action = EntryFragmentDirections.actionEntryFragmentToContactMenuFragment();
        Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment)).navigate(action);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CONTACTS_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(requireContext(), PERMISSION_GRANTED, Toast.LENGTH_SHORT).show();
                enterContactsList();
            }
            else{
                Toast.makeText(requireContext(), PERMISSION_DENIED, Toast.LENGTH_SHORT).show();
            }
        }
    }


}