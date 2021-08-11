package com.example.training_ex1;

import android.app.Application;
import android.content.Context;

/**
 * Contact Application class.
 */
public class ContactsApplication extends Application {

    // Saves a static instance of an Application
    private static Application contactsApp;

    /**
     * onCreate method. Sets the static contactApp to this instance
     */
    @Override
    public void onCreate() {
        super.onCreate();
        ContactsApplication.contactsApp = this;
    }

    /**
     * onTerminate method. Sets the static contactApp to null
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        ContactsApplication.contactsApp=null;
    }

    /**
     * @return the context of the application
     */
    public static Context getAppContext(){
        return ContactsApplication.contactsApp.getApplicationContext();
    }
}
