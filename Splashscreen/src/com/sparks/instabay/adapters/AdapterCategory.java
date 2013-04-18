package com.sparks.instabay.adapters;

import java.util.ArrayList;

import com.sparks.instabay.R;
import com.sparks.instabay.model.KategoriModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterCategory extends BaseAdapter{
	
	Context context;
	ArrayList<KategoriModel> listKategori;
	LayoutInflater inflater;
	
	public AdapterCategory(Context context, ArrayList<KategoriModel> listKategori) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.listKategori = listKategori;
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listKategori.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		View view = arg1;
		ViewHolder holder = null;
		
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.inf_item_kategori, null);
			holder.txtKategori = (TextView)view.findViewById(R.id.txtInfItemKategori);
			view.setTag(holder);
		} else {
			holder = (ViewHolder)view.getTag();
		}
		
		holder.txtKategori.setText(listKategori.get(arg0).getTitle());
		
		return view;
	}
	
	static class ViewHolder{
		TextView txtKategori;
	}

}
