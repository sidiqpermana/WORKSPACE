package com.sparks.instabay.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preference {
	String usernameKey = "username", tokenKey = "token";
	String PREFS_NAME = "instabayPrefs";
	
	SharedPreferences sharedPreferences;
	Editor editor;
	
	Context context;
	
	public Preference(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}
	
	public void setUsername(String username){
		editor.putString(usernameKey, username);
		editor.commit();
	}
	
	public void setToken(String token){
		editor.putString(tokenKey, token);
		editor.commit();
	}
	
	public String getUsername(){
		return sharedPreferences.getString(usernameKey, "");
	}
	
	public String getToken(){
		return sharedPreferences.getString(tokenKey, "");
	}
	
	public void clearPreference(){
		editor.putString(usernameKey, null);
		editor.putString(tokenKey, null);
		editor.commit();
	}
}
