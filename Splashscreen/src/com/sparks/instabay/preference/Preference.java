package com.sparks.instabay.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preference {
	String usernameKey = "username", tokenKey = "token", userIdKey = "userId";
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
	
	public void setUserId(String userId){
		editor.putString(userIdKey, userId);
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
	
	public String getUserId(){
		return sharedPreferences.getString(userIdKey, "");
	}
	
	public void clearPreference(){
		editor.putString(usernameKey, null);
		editor.putString(tokenKey, null);
		editor.commit();
	}
}
