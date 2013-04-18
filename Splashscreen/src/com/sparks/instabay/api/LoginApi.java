package com.sparks.instabay.api;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.sparks.instabay.base.BaseApi;
import com.sparks.instabay.base.BaseApiResultListener;
import com.sparks.instabay.connections.Connection;
import com.sparks.instabay.constants.Constans;

public class LoginApi extends BaseApi{
	private OnApiResultListener onApiResultListener;
	private PostLogin postLogin;
	
	private String email, password, ipAddress;
	
	public interface OnApiResultListener extends BaseApiResultListener{
		public void onApiResultOk(String result);
	}
	
	public LoginApi(Context context, String email, 
			String password, String ipAddress, OnApiResultListener onApiResultListener){
		this.context = context;
		this.email = email;
		this.password = password;
		this.ipAddress = ipAddress;
		this.onApiResultListener = onApiResultListener;
	}
	
	public void callApi(){
		if (postLogin != null) {
			postLogin.cancel(true);
		}
		
		postLogin = new PostLogin();
		postLogin.execute();
	}
	
	public void stopApi(){
		if (postLogin != null) {
			postLogin.cancel(true);
		}
	}
	
	private class PostLogin extends AsyncTask<String, Void, String>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			onApiResultListener.onApiPreCall();
		}
		
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String result = "";
			try {
				result = Connection.login(email, password, ipAddress);
			} catch (Exception e) {
				// TODO: handle exception
				result = "";
				Log.d(Constans.APP_TAG, "Error on LOGIN API : "+e.getMessage());
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
