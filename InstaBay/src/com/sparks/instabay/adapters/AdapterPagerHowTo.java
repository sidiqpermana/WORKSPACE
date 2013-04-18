package com.sparks.instabay.adapters;

import com.sparks.instabay.R;
import com.viewpagerindicator.IconPagerAdapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AdapterPagerHowTo extends PagerAdapter implements IconPagerAdapter{
	
	Context context;
	int[] slideImages = new int[]{
		R.drawable.a, R.drawable.b, R.drawable.c
	};
	
	LayoutInflater inflater;
	ImageView imgSlides;
	
	public AdapterPagerHowTo(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void finishUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getIconResId(int index) {
		// TODO Auto-generated method stub
		return slideImages[index % slideImages.length];
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return slideImages.length;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		View view = container;
		view = inflater.inflate(R.layout.inf_img_howto, null);
		
		imgSlides = (ImageView)view.findViewById(R.id.imgSlideHowTo);
		
		imgSlides.setImageResource(slideImages[position]);
		
		((ViewPager) container).addView(view);
		
		return view;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == ((View) object);
	}
	
	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
