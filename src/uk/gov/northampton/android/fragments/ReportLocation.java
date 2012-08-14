package uk.gov.northampton.android.fragments;

import com.google.android.maps.MapActivity;

import uk.gov.northampton.android.R;
import uk.gov.northampton.android.lib.CustomWebViewActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ReportLocation extends MapActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_job_map);
		
		LocationManager locationManager =
		        (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		LocationProvider provider =
		        locationManager.getProvider(LocationManager.GPS_PROVIDER);
		
	}

	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("Report Tab", "Paused!!");
	}

	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("Report Tab", "Resumed!!");
	}

	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		LocationManager locationManager =
	            (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

	    if (!gpsEnabled) {
	        // Build an alert dialog here that requests that the user enable
	        // the location services, then when the user clicks the "OK" button,
	        //enableLocationSettings();
	    }
	}

	private void enableLocationSettings() {
			Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(settingsIntent);
	}

	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("Report Tab", "Stopped!!");
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
