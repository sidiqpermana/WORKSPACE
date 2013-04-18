package com.sparks.instabay.libs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class ConnectivityReceiver extends BroadcastReceiver{

	public static interface onNetworkAvailableListener{
		public void onNetworkAvailable();
		public void onNetworkUnAvailable();
	}
	
	private final ConnectivityManager connectivityManager;
	private onNetworkAvailableListener onAvailableListener;
	private boolean connection = false;
	
	public ConnectivityReceiver(Context context){
		connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		checkConnectionOnDemand();
	}
	
	private void checkConnectionOnDemand() {
		// TODO Auto-generated method stub
		final NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		if (info == null || info.getState() != State.CONNECTED) {
			if (connection == true) {
				connection = false;
				if (onAvailableListener != null) {
					onAvailableListener.onNetworkUnAvailable();
				}
			}
		}else{
			if (connection == false) {
				connection = true;
				if (onAvailableListener != null) {
					onAvailableListener.onNetworkAvailable();
				}
			}
		}
	}

	public void unbind(Context context){
		context.unregisterReceiver(this);
	}
	
	public void bind(Context context){
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		context.registerReceiver(this, filter);
		checkConnectionOnDemand();
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (connection == true && intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)) {
			connection = false;
			if (onAvailableListener != null) {
				onAvailableListener.onNetworkUnAvailable();
			}
		} else if(connection == false && !intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)){
			connection = true;
			if (onAvailableListener != null) {
				onAvailableListener.onNetworkAvailable();
			}
		}
	}
	
	public boolean hasConnection(){
		return connection;
	}
	
	public void setOnNetworkAvailableListener(onNetworkAvailableListener listener){
		this.onAvailableListener = listener;
	}
}
