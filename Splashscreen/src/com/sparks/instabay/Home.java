package com.sparks.instabay;

import com.sparks.instabay.base.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Home extends BaseActivity {
	
	private Button btnKategori;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		initViews();
		initActions();
	}
	
	public static void toHome(Activity activity){
		Intent intent = new Intent(activity, Home.class);
		activity.startActivity(intent);
		
		BaseActivity.slideIn(activity);
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		
		btnKategori = (Button)findViewById(R.id.btnHomeKategori);
	}
	
	@Override
	public void initActions() {
		// TODO Auto-generated method stub
		super.initActions();
		
		btnKategori.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Kategori.toKategori(Home.this);
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		BaseActivity.slideOut(Home.this);
	}
}
