package com.example.training_ex1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Main Activity class to display contact's application home page
 * This activity holds a RecyclerView for each contact.
 */
public class MainActivity extends AppCompatActivity{

    private static final int CONTACTS_REQUEST_CODE = 1;
    private static final String PERMISSION_GRANTED = "Permission to contacts granted";
    private static final String PERMISSION_DENIED = "Permission denied. You must allow access to contacts in order to use the app";


    /**
     * On create method. Set the home page and the adapter for the RecyclerView of this Activity
     * @param savedInstanceState saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CONTACTS_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, PERMISSION_GRANTED, Toast.LENGTH_SHORT).show();
                navigateToContactMenuFragment();
            } else {
                Toast.makeText(MainActivity.this, PERMISSION_DENIED, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToContactMenuFragment(){
        NavDirections action = EntryFragmentDirections.actionEntryFragmentToContactMenuFragment();
        Navigation.findNavController(findViewById(R.id.nav_host_fragment)).navigate(action);
    }
}
