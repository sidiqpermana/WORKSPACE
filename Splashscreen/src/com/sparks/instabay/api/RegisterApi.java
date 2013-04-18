package com.sparks.instabay.api;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.sparks.instabay.base.BaseApi;
import com.sparks.instabay.base.BaseApiResultListener;
import com.sparks.instabay.connections.Connection;
import com.sparks.instabay.constants.Constans;

public class RegisterApi extends BaseApi{
	private OnApiResultListener onApiResultListener;
	private PostRegister postRegister;
	
	private String email, firstName, lastName, password, ipAddress;
	
	public interface OnApiResultListener extends BaseApiResultListener{
		public void onApiResultOk(String result);
	}
	
	public RegisterApi(Context context, String email, String firstName, String lastName, 
			String password, String ipAddress, OnApiResultListener onApiResultListener){
		this.context = context;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.ipAddress = ipAddress;
		this.onApiResultListener = onApiResultListener;
	}
	
	public void callApi(){
		if (postRegister != null) {
			postRegister.cancel(true);
		}
		
		postRegister = new PostRegister();
		postRegister.execute();
	}
	
	public void stopApi(){
		if (postRegister != null) {
			postRegister.cancel(true);
		}
	}
	
	private class PostRegister extends AsyncTask<String, Void, String>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			onApiResultListener.onApiPreCall();
		}
		
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String result = "";
			try {
				result = Connection.register(email, firstName, lastName, password, ipAddress);
			} catch (Exception e) {
				// TODO: handle exception
				result = "";
				Log.d(Constans.APP_TAG, "Error on REGISTER API : "+e.getMessage());
			}
			
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			if (result==null || 
					result.equals("")) {
				onApiResultListener.onApiResultError(result);
			} else {
				onApiResultListener.onApiResultOk(result);
			}
		}
		
	}
}
