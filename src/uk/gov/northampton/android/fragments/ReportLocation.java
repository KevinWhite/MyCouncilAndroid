package uk.gov.northampton.android.fragments;

import java.util.List;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;

import uk.gov.northampton.android.R;
import uk.gov.northampton.android.lib.CustomWebViewActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
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

public class ReportLocation extends Activity {
	
	private LocationManager lm;
	private final Criteria lc = new Criteria();
	private static int minUpdateTime = 0;
	private static int minUpdateDistance =0;
	
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_job_map);
		
		Criteria lc = new Criteria();
		lc.setAccuracy(Criteria.ACCURACY_FINE);
		lc.setPowerRequirement(Criteria.POWER_LOW);
		lc.setAltitudeRequired(false);
		lc.setBearingRequired(false);
		lc.setCostAllowed(true);
		lc.setSpeedRequired(false);
		
		String svcName = Context.LOCATION_SERVICE;
		lm = (LocationManager) getSystemService(svcName);
		
	}

	public void onPause() {
		super.onPause();
		Log.d("Report Tab", "Paused");
	}

	public void onResume(){
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

	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		unregisterAllListeners();
		Log.d("Report Tab", "Stopped!!");
	}
	
	private void enableLocationSettings() {
		Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(settingsIntent);
	}
	
	private void unregisterAllListeners(){
		lm.removeUpdates(bestProviderListener);
		lm.removeUpdates(bestAvailableProviderListener);
	}
	
	private void reactToLocationChange(Location loc){
		//TODO
	}
	
	private void registerListener(){
		unregisterAllListeners();
		String bestProvider = lm.getBestProvider(lc, false);
		String bestAvailableProvider = lm.getBestProvider(lc, true);
		
		Log.d("Location Manager",bestProvider + " / " + bestAvailableProvider);
		
		if(bestProvider == null){
			Log.d("Location Manager","No location provider exists on device");
		}
		else if(bestProvider.equals(bestAvailableProvider)){
			lm.requestLocationUpdates(bestAvailableProvider, minUpdateTime, minUpdateDistance, bestAvailableProviderListener);
		}
		else{
			lm.requestLocationUpdates(bestProvider, minUpdateTime, minUpdateDistance, bestProviderListener);
			
			if(bestAvailableProvider != null){
				lm.requestLocationUpdates(bestAvailableProvider, minUpdateTime, minUpdateDistance, bestAvailableProviderListener);
			}
			else{
				List<String> allProviders = lm.getAllProviders();
				for(String provider : allProviders){
					lm.requestLocationUpdates(provider, minUpdateTime, minUpdateDistance, bestProviderListener);
				}
			}
		}
		
	}
	
	private LocationListener bestProviderListener = new LocationListener(){

		@Override
		public void onLocationChanged(Location loc) {
			reactToLocationChange(loc);
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			registerListener();
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	};
	
	private LocationListener bestAvailableProviderListener = new LocationListener(){

		@Override
		public void onLocationChanged(Location loc) {
			reactToLocationChange(loc);
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			registerListener();
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	};
	
	
}
