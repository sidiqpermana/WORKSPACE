package com.sparks.instabay.libs;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.http.conn.util.InetAddressUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.widget.Toast;

public class Utils {
	private static double EARTH_RADIUS_KM = 6384;
	public static String calc_distance(Context context, String start_longlat, String end_longlat){
		double sLat = Double.parseDouble(start_longlat.substring(0, start_longlat.indexOf(",")-1));
		double sLng = Double.parseDouble(start_longlat.substring(start_longlat.indexOf(",")+1));
		double eLat = Double.parseDouble(end_longlat.substring(0, end_longlat.indexOf(",")-1));
		double eLng = Double.parseDouble(end_longlat.substring(end_longlat.indexOf(",")+1));
		double dis;
		double jrk;
		String distance = null;
		double d2r = (Math.PI / 180);
	    double dLat = (eLat - sLat) * d2r;
	    double dLon = (eLng - sLng) * d2r;
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
	    + Math.cos(sLat * d2r) * Math.cos(eLat * d2r)
	            * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	      double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    dis = EARTH_RADIUS_KM * c * 1000;
	    if(dis>10000){
	    	jrk = Double.valueOf(Math.round(dis/1000));
	    	distance = Double.toString(jrk)+" Km";
	    }else if(dis > 0 && dis < 100){
	    	jrk = Double.valueOf(Math.round(dis));
	    	distance = Double.toString(jrk)+" m";
	    }else if(dis > 200 && dis < 1000){
	    	jrk = Double.valueOf(Math.round(dis/10));
	    	distance = Double.toString(jrk)+" Km";
	    }else{
	    	jrk = Double.valueOf(Math.round(dis/1000));
	    	distance = Double.toString(jrk)+" Km";
	    }
	    return distance;
	}
	
	public static Bitmap getResizedBitmap(Bitmap bm){
    	float height2;
    	float width2;
    	//float x;
    	
    	//int newHeight = 640;
    	//int newWidth = 640;
    	
    	int width = bm.getWidth();
    	int height = bm.getHeight();
    	
    	Bitmap resizeBitmap = null;
    	Bitmap cropBitmap = null;
    	
    	if(width > height){
    		height2 = 640;
    		width2 = ((float)640/height)*width;
    		resizeBitmap = Bitmap.createScaledBitmap(bm, Math.round(width2), Math.round(height2), true);
    		
    		
    		cropBitmap = Bitmap.createBitmap(resizeBitmap, (Math.round(width2)-Math.round(height2))/2, 0, Math.round(height2), Math.round(height2));
    	}
    	else{
    		width2 = 640;
    		height2 = ((float)640/width)*height;
    		resizeBitmap = Bitmap.createScaledBitmap(bm, Math.round(width2), Math.round(height2), true);
    		
    		
    		cropBitmap = Bitmap.createBitmap(resizeBitmap,  0, (Math.round(height2)-Math.round(width2))/2, Math.round(width2), Math.round(width2));
    	}
		//float scaleWidth = ((float)newWidth)/width;
    	return cropBitmap;
    	
    }
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
	        bitmap.getHeight(), Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);
	 
	    final int color = 0xff424242;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	    final RectF rectF = new RectF(rect);
	    final float roundPx = 6;
	 
	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
	 
	    paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);
	 
	    return output;
	  }
	
	public static String generatePassword(){
		String karakter = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		int len = 8;
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(karakter.charAt(rnd.nextInt(karakter.length())));
		}
		return sb.toString();
	}
	
	public static String filter(String str) {
	    StringBuilder filtered = new StringBuilder(str.length());
	    for (int i = 0; i < str.length(); i++) {
	        char current = str.charAt(i);
	        if (current >= 0x20 && current <= 0x7e) {
	            filtered.append(current);
	        }
	    }

	    return filtered.toString();
	}

	public static String goToGeocoder(Activity mContext, double lat, double lng) throws IOException{
		List<Address> almt;
		String alamat = null;
		//StringBuilder sb = new StringBuilder();
		try {
			Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
			almt = geocoder.getFromLocation(lat, lng, 5);
			if (almt.size() > 0 && almt !=null) {
				
				Address almtSkrng = almt.get(0);
				//String admin_area = sb.append(almtSkrng.getAdminArea()).toString();
				//String postalcode = sb.append(almtSkrng.getPostalCode()).toString();
				if (almtSkrng.getAddressLine(0).equals(null)||almtSkrng.getAddressLine(0).equals("")) {
					alamat = almtSkrng.getFeatureName();
				} 
				alamat = almtSkrng.getAddressLine(0)+" "+almtSkrng.getAdminArea();
			}else{
				alamat = "";
			}
			
		} catch (IOException e) {
			// TODO: handle exception
			//Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			alamat = "";
			Log.e("yotomo", e.getMessage());
		}
		
		return alamat;
	}
	
	public static String md5(String in) {
	    MessageDigest digest;
	    try {
	        digest = MessageDigest.getInstance("MD5");
	        digest.reset();
	        digest.update(in.getBytes());
	        byte[] a = digest.digest();
	        int len = a.length;
	        StringBuilder sb = new StringBuilder(len << 1);
	        for (int i = 0; i < len; i++) {
	            sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
	            sb.append(Character.forDigit(a[i] & 0x0f, 16));
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
	    return null;
	}
	
	public static void showMessage(Activity activity, int message){
		Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
	}
	
	public static void showMessage(Activity activity, String message){
		Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
	}
	
	public static String getIPAddress() {
		boolean useIPv4 = true;
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress().toUpperCase();
                        boolean isIPv4 = false;
                        isIPv4 =  InetAddressUtils.isIPv4Address(sAddr); 
                        if (useIPv4) {
                            if (isIPv4) 
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 port suffix
                                return delim<0 ? sAddr : sAddr.substring(0, delim);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }

}
