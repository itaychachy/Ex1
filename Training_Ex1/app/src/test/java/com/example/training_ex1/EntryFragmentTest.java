package com.example.training_ex1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static com.google.common.truth.Truth.*;
import android.widget.Button;

//@RunWith(RobolectricTestRunner.class)
@Config(manifest = "AndroidManifest.xml")
public class EntryFragmentTest {

    private MainActivity activity;

    @Before
    public void setUp(){
        this.activity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void TestActivityNotNull()
    {
        assertThat( activity ).isNotNull();
    }

    @Test
    public void TestActivityStartsWithEntryFragment()
    {
        assertThat(activity.getFragmentManager().findFragmentById(R.id.entryFragment)).isNotNull();
    }

    @Test
    public void TestButtonText(){
        Button button = activity.findViewById(R.id.button);

        String buttonText = (String) button.getText();

        assertThat(buttonText).isEqualTo("TO CONTACT LIST");
    }
}
