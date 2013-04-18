package com.sparks.instabay.connections;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.sparks.instabay.constants.Constans;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Connection {
	
	public static String get(String url) throws IOException
	{
		String apiResponse = "";
		try {
			String urlFix = url;
			HttpClient client = new DefaultHttpClient();
			HttpGet post = new HttpGet(urlFix);
			HttpResponse response = client.execute(post);
	        
			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("Got HTTP " + statusCode + " ("
						+ response.getStatusLine().getReasonPhrase() + ')');
			}
			
	        HttpEntity entity = response.getEntity();
	        InputStream is = entity.getContent();
	        if(entity != null)
	        {
	        	
	        	//"iso-8859-1"
	        	BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
	        	
		        StringBuilder sb = new StringBuilder();
		
		        String line = null;
		        while ((line = reader.readLine()) != null)
		        {
		                sb.append(line + "\n");
		        }
		        is.close();
		        
		        apiResponse = sb.toString();
	        }
	        else
	        {
	        	apiResponse = "";
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return apiResponse;
	}
	
	private static String res = "";
	
	public static String uploadImage(String pathFile, String userId, String checkInId, String fsqVenueId) throws NoSuchAlgorithmException{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://www.yotomo.com/mobileapi/uploadpicture");
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
		nameValuePairs.add(new BasicNameValuePair("picture", pathFile));
		nameValuePairs.add(new BasicNameValuePair("userId", userId));
		nameValuePairs.add(new BasicNameValuePair("checkInId", checkInId));
		nameValuePairs.add(new BasicNameValuePair("fsqVenueId", fsqVenueId));
		try {
			MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			for(int index=0; index < nameValuePairs.size(); index++) {
	            if(nameValuePairs.get(index).getName().equalsIgnoreCase("picture")){
	                // If the key equals to "image", we use FileBody to transfer the data
	                entity.addPart(nameValuePairs.get(index).getName(), new FileBody(new File (nameValuePairs.get(index).getValue())));
	            }else{
	                // Normal string data
	                entity.addPart(nameValuePairs.get(index).getName(), new StringBody(nameValuePairs.get(index).getValue()));
	            }
	        }
			//entity.addPart(nameValuePairs, new FileBody(new File(pathFile)));
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity enty = response.getEntity();
		    InputStream is = enty.getContent();
	        {
	        	BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);

		        StringBuilder sb = new StringBuilder();
		
		        String line = null;
		        while ((line = reader.readLine()) != null)
		        {
		                sb.append(line + "\n");
		        }
		        is.close();
		        res =  sb.toString();
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.d("NAME APP", e.getMessage());
		}
		return res;
    }
	
	public static String register(String email, String firstName, String 
			lastName, String password, String ipAddress) throws NoSuchAlgorithmException{
		
		String resultRegister = "";
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(Constans.EP_USERS_REGISTER);
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
		nameValuePairs.add(new BasicNameValuePair("email", email));
		nameValuePairs.add(new BasicNameValuePair("firstName", firstName));
		nameValuePairs.add(new BasicNameValuePair("lastName", lastName));
		nameValuePairs.add(new BasicNameValuePair("password", password));
		nameValuePairs.add(new BasicNameValuePair("ipAddress", ipAddress));
		try {
			
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity enty = response.getEntity();
		    InputStream is = enty.getContent();
	        {
	        	BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);

		        StringBuilder sb = new StringBuilder();
		
		        String line = null;
		        while ((line = reader.readLine()) != null)
		        {
		                sb.append(line + "\n");
		        }
		        is.close();
		        resultRegister =  sb.toString();
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.d(Constans.APP_TAG, e.getMessage());
		}
		return resultRegister;
    }
	
	public static String login(String email, String password, String ipAddress) throws NoSuchAlgorithmException{
		
		String resultLogin = "";
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(Constans.EP_USERS_LOGIN);
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
		nameValuePairs.add(new BasicNameValuePair("email", email));
		nameValuePairs.add(new BasicNameValuePair("password", password));
		nameValuePairs.add(new BasicNameValuePair("ipAddress", ipAddress));
		try {
			
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity enty = response.getEntity();
		    InputStream is = enty.getContent();
	        {
	        	BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);

		        StringBuilder sb = new StringBuilder();
		
		        String line = null;
		        while ((line = reader.readLine()) != null)
		        {
		                sb.append(line + "\n");
		        }
		        is.close();
		        resultLogin =  sb.toString();
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.d(Constans.APP_TAG, e.getMessage());
		}
		return resultLogin;
    }
	

	public static boolean isConnectInet(Context context){
		NetworkInfo networkInfo = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
		if (networkInfo==null || !networkInfo.isConnected()) {
			return false;
		} 
		if (networkInfo.isRoaming()) {
			return true;
		}
		
		return true;
	}
}
