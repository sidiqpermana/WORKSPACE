package com.sparks.instabay.base;

public interface BaseApiResultListener {
	public void onApiPreCall();
	public void onApiResultError(String message);
}
