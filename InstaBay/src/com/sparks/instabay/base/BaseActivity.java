package com.sparks.instabay.base;

import com.sparks.instabay.R;
import com.sparks.instabay.libs.ConnectivityReceiver.onNetworkAvailableListener;
import com.sparks.instabay.libs.Utilities;

import android.app.Activity;

public class BaseActivity extends Activity implements onNetworkAvailableListener{

	@Override
	public void onNetworkAvailable() {
		// TODO Auto-generated method stub
		Utilities.showMessage(BaseActivity.this, R.string.internet_lost);
	}

	@Override
	public void onNetworkUnAvailable() {
		// TODO Auto-generated method stub
		
	}
	
	public void initAnim(){}
	public void initIntent(){}
	public void initViews(){}
	public void initLibs(){}
	public void initProcess(){}
	public void initActions(){}
	
	public static void slideIn(Activity activity){
		activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_still);
	}
	
	public static void slideOut(Activity activity){
		activity.overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
	}

}
