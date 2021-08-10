package com.example.training_ex1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static com.google.common.truth.Truth.*;
import android.widget.Button;
import android.widget.FrameLayout;

@Config(manifest = "AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class EntryFragmentTest {

    private MainActivity activity;

    @Test
    public void temp(){
        assertThat(5).isAtLeast(2);
    }


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
    public void TestFragmentMargins(){
        Button button = activity.findViewById(R.id.button);
        int bottomMargin = ((FrameLayout.LayoutParams) button.getLayoutParams()).bottomMargin;
        assertThat(bottomMargin).isEqualTo(0);
        int topMargin = ((FrameLayout.LayoutParams) button.getLayoutParams()).topMargin;
        assertThat(topMargin).isEqualTo(0);
        int rightMargin = ((FrameLayout.LayoutParams) button.getLayoutParams()).rightMargin;
        assertThat(rightMargin).isEqualTo(0);
        int leftMargin = ((FrameLayout.LayoutParams) button.getLayoutParams()).leftMargin;
        assertThat(leftMargin).isEqualTo(0);
    }

    @Test
    public void TestButtonText(){
        Button button = activity.findViewById(R.id.button);
        String buttonText = (String) button.getText();
        assertThat(buttonText).matches("TO CONTACT LIST");
    }
}
