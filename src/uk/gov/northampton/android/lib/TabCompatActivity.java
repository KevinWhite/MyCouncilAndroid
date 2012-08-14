package uk.gov.northampton.android.lib;

import uk.gov.northampton.android.lib.TabHelper;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * A base activity that defers tab functionality to a {@link TabHelper}.
 *
 * When building an activity with tabs, extend this class in order to provide compatibility with API
 * level 5 and above. Using this class along with the {@link TabHelper} and {@link com.example.android.tabcompat.lib.CompatTab}
 * classes, you can build a tab UI that's built using the {@link android.app.ActionBar} on
 * Honeycomb+ and the {@link android.widget.TabWidget} on all older versions.
 *
 * The {@link TabHelper} APIs obfuscate all the compatibility work for you.
 */
public abstract class TabCompatActivity extends FragmentActivity {

    TabHelper mTabHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTabHelper = TabHelper.createInstance(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mTabHelper.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTabHelper.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Returns the {@link TabHelper} for this activity.
     */
    protected TabHelper getTabHelper() {
        mTabHelper.setUp();
        return mTabHelper;
    }
}
