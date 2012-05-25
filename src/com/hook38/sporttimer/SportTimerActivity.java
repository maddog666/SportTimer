package com.hook38.sporttimer;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.hook38.sporttimer.controller.ActivityController;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

abstract public class SportTimerActivity extends FragmentActivity {
	protected ActivityController controller;
	private Vibrator vibrator;
	private LocationManager locationManager;
	private String TAG = "SportTimerActivity"; 
	public ActivityController getController(){
		return controller;
	}
	
	protected Vibrator getVibrator(){
		if(this.vibrator == null) {
			this.vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		}
		return this.vibrator;
	}
	
	/*
	 * This is for free version with advertisement
	abstract protected AdView getAdView();
	
	protected void configAdView() {
		AdRequest request = new AdRequest();
		
		locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		String locationProvider =  LocationManager.NETWORK_PROVIDER;		
		locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
		Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
		
		request.setLocation(lastKnownLocation);
		Log.d(TAG, request.getLocation().toString());
		this.getAdView().loadAd(request);
	}
	
	@Override
	public void onDestroy(){
		if(this.getAdView() != null){
			this.getAdView().destroy();
		}
		super.onDestroy();
	}
	
	LocationListener locationListener = new LocationListener() {

		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			
		}

		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
	};
	*/
	
}
