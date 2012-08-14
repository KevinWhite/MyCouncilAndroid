package uk.gov.northampton.android;

import uk.gov.northampton.android.R;
import uk.gov.northampton.android.lib.CompatTab;
import uk.gov.northampton.android.lib.CompatTabListener;
import uk.gov.northampton.android.lib.TabCompatActivity;
import uk.gov.northampton.android.lib.TabHelper;
import uk.gov.northampton.android.fragments.*;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class MainActivity extends TabCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabHelper tabHelper = getTabHelper();

        CompatTab socialTab = tabHelper.newTab("Social")
                .setText(R.string.tab_social)
                .setTabListener(new InstantiatingTabListener(this, Social.class));
        

        CompatTab reportTab = tabHelper.newTab("Report")
                .setText(R.string.tab_report)
                .setTabListener(new InstantiatingTabListener(this, Report.class));
        
        CompatTab findTab = tabHelper.newTab("Find")
        		.setText(R.string.tab_find)
        		.setTabListener(new InstantiatingTabListener(this, Find.class));
        
        CompatTab servicesTab = tabHelper.newTab("Services")
				.setText(R.string.tab_services)
				.setTabListener(new InstantiatingTabListener(this, Services.class));
        
        CompatTab contactTab = tabHelper.newTab("Contact")
				.setText(R.string.tab_contact)
				.setTabListener(new InstantiatingTabListener(this, Contact.class));

        tabHelper.addTab(socialTab);
        tabHelper.addTab(reportTab);
        tabHelper.addTab(findTab);
        tabHelper.addTab(servicesTab);
        tabHelper.addTab(contactTab);
    }

    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("Main Activity", "Resumed!!!");
	}

	/**
     * Implementation of {@link CompatTabListener} to handle tab change events. This implementation
     * instantiates the specified fragment class with no arguments when its tab is selected.
     */
    public static class InstantiatingTabListener implements CompatTabListener {

        private final TabCompatActivity mActivity;
        private final Class mClass;

        /**
         * Constructor used each time a new tab is created.
         *
         * @param activity The host Activity, used to instantiate the fragment
         * @param cls      The class representing the fragment to instantiate
         */
        public InstantiatingTabListener(TabCompatActivity activity, Class<? extends Fragment> cls) {
            mActivity = activity;
            mClass = cls;
        }

        /* The following are each of the ActionBar.TabListener callbacks */
        @Override
        public void onTabSelected(CompatTab tab, FragmentTransaction ft) {
            // Check if the fragment is already initialized
            Fragment fragment = tab.getFragment();
            if (fragment == null) {
                // If not, instantiate and add it to the activity
                fragment = Fragment.instantiate(mActivity, mClass.getName());
                tab.setFragment(fragment);
                ft.add(android.R.id.tabcontent, fragment, tab.getTag());
            } else {
                // If it exists, simply attach it in order to show it
                ft.attach(fragment);
            }
        }

        @Override
        public void onTabUnselected(CompatTab tab, FragmentTransaction ft) {
            Fragment fragment = tab.getFragment();
            if (fragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(fragment);
            }
        }

        @Override
        public void onTabReselected(CompatTab tab, FragmentTransaction ft) {
            // User selected the already selected tab. Do nothing.
        }
    }
}

