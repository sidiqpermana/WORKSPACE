package com.sparks.instabay.api;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.sparks.instabay.base.BaseApi;
import com.sparks.instabay.base.BaseApiResultListener;
import com.sparks.instabay.connections.Connection;
import com.sparks.instabay.constants.Constans;

public class KategoriApi extends BaseApi{
	private OnApiResultListener onApiResultListener;
	private LoadKategori loadKategori;
	
	public interface OnApiResultListener extends BaseApiResultListener{
		public void onApiResultOk(String result);
	}
	
	public KategoriApi(Context context, OnApiResultListener onApiResultListener){
		this.context = context;
		this.onApiResultListener = onApiResultListener;
	}
	
	public void callApi(){
		if (loadKategori != null) {
			loadKategori.cancel(true);
		}
		
		loadKategori = new LoadKategori();
		loadKategori.execute(Constans.EP_HOME_TIMELINE);
	}
	
	public void stopApi(){
		if (loadKategori != null) {
			loadKategori.cancel(true);
		}
	}
	
	private class LoadKategori extends AsyncTask<String, Void, String>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			onApiResultListener.onApiPreCall();
		}
		
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String result = "";
			try {
				result = Connection.get(params[0]);
			} catch (Exception e) {
				// TODO: handle exception
				result = "";
				Log.d(Constans.APP_TAG, "Error on Kategori API : "+e.getMessage());
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
