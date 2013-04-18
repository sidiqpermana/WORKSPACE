package com.sparks.instabay;

import android.os.AsyncTask;
import android.os.Bundle;

import com.sparks.instabay.base.BaseActivity;

public class Splashscreen extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		
		new Delay().execute();
	}
	
	private class Delay extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			Howto.toHowTo(Splashscreen.this);
			finish();
		}
	}
}
