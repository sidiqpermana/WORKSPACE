package com.sparks.instabay;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sparks.instabay.adapters.AdapterPagerHowTo;
import com.sparks.instabay.base.BaseActivity;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class Howto extends BaseActivity{
	
	private Button btnLogin, btnRegister;
	private ViewPager viewPager;
	private PageIndicator pageIndicator;
	private AdapterPagerHowTo adapterPagerHowTo;
	
	private boolean isSlideRunning = false;
	private Timer myTimer;
	
	private int currentPos = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howto);
		
		initViews();
		initProcess();
		initActions();
	}
	
	@Override
	public void initIntent() {
		// TODO Auto-generated method stub
		super.initIntent();
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		
		btnLogin = (Button)findViewById(R.id.btnHowToLogin);
		btnRegister = (Button)findViewById(R.id.btnHowToRegister);
		
		viewPager = (ViewPager)findViewById(R.id.pager);
		pageIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
	}
	
	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		super.initProcess();
		
		adapterPagerHowTo = new AdapterPagerHowTo(Howto.this);
		viewPager.setAdapter(adapterPagerHowTo);
		
		viewPager.setPageTransformer(false, new PageTransformer() {
			
			@SuppressLint("NewApi")
			@Override
			public void transformPage(View view, float position) {
				// TODO Auto-generated method stub
				final float invt = Math.abs(Math.abs(position) - 1);
                view.setAlpha(invt);
			}
		});
		
		pageIndicator.setViewPager(viewPager);
		viewPager.setCurrentItem(currentPos);
		
		if (!isSlideRunning) {
			myTimer = new Timer();
    	    myTimer.schedule(new TimerTask() {          
    	        @Override
    	        public void run() {
    	            TimerMethod();
    	        }

    	    }, 0, 5000);
    	    isSlideRunning = true;
		}
		
	}
	
	private void TimerMethod()
	{
		 this.runOnUiThread(Timer_Tick);
	}
	
	
	private Runnable Timer_Tick = new Runnable() {
	    public void run(){
	    	
	    	viewPager.setCurrentItem(currentPos);
	    	viewPager.setPageTransformer(false, new PageTransformer() {
				
				@SuppressLint("NewApi")
				@Override
				public void transformPage(View view, float position) {
					// TODO Auto-generated method stub
					final float invt = Math.abs(Math.abs(position) - 1);
	                view.setAlpha(invt);
				}
			});
	    	if(currentPos==3)
	    	{
	    		currentPos=-1;
	    	}
	    	currentPos++;
	    }
	 
	};
	
	@Override
	public void initActions() {
		// TODO Auto-generated method stub
		super.initActions();
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Login.toLogin(Howto.this);
			}
		});
		
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Register.toRegister(Howto.this);
			}
		});
		
		
	}
	
	public static void toHowTo(Activity activity){
		Intent intent = new Intent(activity, Howto.class);
		activity.startActivity(intent);
		BaseActivity.slideIn(activity);
	}
}
