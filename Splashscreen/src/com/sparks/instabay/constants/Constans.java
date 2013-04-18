/** 
 * This class is constants variable
 * VARIABLE FORMAT : /CLASS/FUNCTIONS followed by params
 * example : /users/register 
 * **/

package com.sparks.instabay.constants;

public class Constans {
	public static final String APP_TAG = "INSTABAY";
	
	public static String BASE_SAMPLE_URL = "http://www.sidiq.nusantarabetastudio.com";
	public static String EP_HOME_TIMELINE = BASE_SAMPLE_URL+"/category.php";
	
	public static final String BASE_URL = "http://www.sparkworks.co.id/instabay/api/v1";
	
	public static final String EP_USERS_REGISTER = BASE_URL+"/users/register";
	public static final String EP_USERS_LOGIN = BASE_URL+"/users/authenticate";
	
	public static final int AUTH_SUCCESS = 100;
	public static final int AUTH_FAILED = 101;
	public static final int REGIS_SUCCESS = 102;
	public static final int REGIS_FAILED = 103;
	public static final int RESET_PASS_SUCCESS = 104;
	public static final int SYSTEM_ERROR = 1;
	
	public static final String objectIdKey = "objectID";
}
