package com.sparks.instabay;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.sparks.instabay.adapters.AdapterCategory;
import com.sparks.instabay.api.KategoriApi;
import com.sparks.instabay.base.BaseActivity;
import com.sparks.instabay.connections.Connection;
import com.sparks.instabay.constants.Constans;
import com.sparks.instabay.libs.Utils;
import com.sparks.instabay.model.KategoriModel;

public class Kategori extends BaseActivity{
	
	private ListView lvKategori;
	private ProgressBar indicator;
	
	private ArrayList<KategoriModel> listKategori; 
	private AdapterCategory adapterCategory;
	
	private KategoriApi kategoriApi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kategori);
		
		initViews();
		initLibs();
		initProcess();
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		
		lvKategori = (ListView)findViewById(R.id.lvKategori);
		indicator = (ProgressBar)findViewById(R.id.indicator);
	}
	
	@Override
	public void initLibs() {
		// TODO Auto-generated method stub
		super.initLibs();
		
		listKategori = new ArrayList<KategoriModel>();
	}
	
	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		super.initProcess();
		
		if (Connection.isConnectInet(Kategori.this)) {
			kategoriApi = new KategoriApi(Kategori.this, apiResultListener);
			kategoriApi.callApi();
		}
	}
	
	public static void toKategori(Activity activity){
		Intent intent = new Intent(activity, Kategori.class);
		activity.startActivity(intent);
		
		BaseActivity.slideIn(activity);
	}
	
	KategoriApi.OnApiResultListener apiResultListener = new KategoriApi.OnApiResultListener() {
		
		@Override
		public void onApiResultError(String message) {
			// TODO Auto-generated method stub
			indicator.setVisibility(ProgressBar.GONE);
			Utils.showMessage(Kategori.this, R.string.error_response);
		}
		
		@Override
		public void onApiPreCall() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onApiResultOk(String result) {
			// TODO Auto-generated method stub
			indicator.setVisibility(ProgressBar.GONE);
			fetchResponse(result);
		}
	};
	
	public void fetchResponse(String result){
		KategoriModel kategori = null;
		try {
			JSONArray jsonArray = new JSONArray(result);
			if (jsonArray.length()>0) {
				
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject item = jsonArray.getJSONObject(i);
					
					kategori = new KategoriModel(item.optString("id"), 
							item.optString("nama"));
					
					listKategori.add(kategori);
				}
				lvKategori.setVisibility(ListView.VISIBLE);
				adapterCategory = new AdapterCategory(Kategori.this, listKategori);
				lvKategori.setAdapter(adapterCategory);
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.d(Constans.APP_TAG, "FETCH KATEGORI RESPONSE : "+e.getMessage());
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		BaseActivity.slideOut(Kategori.this);
		kategoriApi.stopApi();
	}
}
