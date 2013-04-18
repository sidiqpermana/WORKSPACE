package com.sparks.instabay;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sparks.instabay.api.RegisterApi;
import com.sparks.instabay.base.BaseActivity;
import com.sparks.instabay.connections.Connection;
import com.sparks.instabay.constants.Constans;
import com.sparks.instabay.libs.Utils;
import com.sparks.instabay.preference.Preference;

public class Register extends BaseActivity{
	
	private TextView txtTerm;
	private EditText edtEmail, edtFirstName, edtLastName, edtPassword;
	private Button btnRegister;
	
	private RegisterApi registerApi;
	
	private ProgressDialog dialog;
	
	private Preference preference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		initViews();
		initLibs();
		initProcess();
		initActions();
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		txtTerm = (TextView)findViewById(R.id.txtRegisterTerm);
		
		edtEmail = (EditText)findViewById(R.id.edtRegisterEmail);
		edtFirstName = (EditText)findViewById(R.id.edtRegisterFirstName);
		edtLastName = (EditText)findViewById(R.id.edtRegisterLastName);
		edtPassword = (EditText)findViewById(R.id.edtRegisterPassword);
		
		btnRegister = (Button)findViewById(R.id.btnRegisterSubmit);
	}
	
	@Override
	public void initLibs() {
		// TODO Auto-generated method stub
		super.initLibs();
		
		preference = new Preference(Register.this);
	}
	
	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		super.initProcess();
		
		String teks = "<b><u>Terms and Policy</u></b>";
		
		txtTerm.setText(Html.fromHtml(teks));
	}
	
	@Override
	public void initActions() {
		// TODO Auto-generated method stub
		super.initActions();
		
		txtTerm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				TermAndConditions.toTermAndConditions(Register.this);
			}
		});
		
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String email = edtEmail.getText().toString().trim();
				String firstName = edtFirstName.getText().toString().trim();
				String lastName = edtLastName.getText().toString().trim();
				String password = edtPassword.getText().toString().trim();
				String ipAddress = Utils.getIPAddress();
				if (isStillEmpty(email, firstName, 
						lastName, password)) {
					Utils.showMessage(Register.this, R.string.confirm_registration);
				}else{
					if (Connection.isConnectInet(Register.this)) {
						registerApi = new RegisterApi(Register.this, email, firstName, 
								lastName, password, ipAddress, onApiResultListener);
						registerApi.callApi();
					} else {
						Utils.showMessage(Register.this, R.string.error_no_connection);
					}
				}
			}
		});
	}
	
	RegisterApi.OnApiResultListener onApiResultListener = new RegisterApi.OnApiResultListener() {
		
		@Override
		public void onApiResultError(String message) {
			// TODO Auto-generated method stub
			Utils.showMessage(Register.this, message);
		}
		
		@Override
		public void onApiPreCall() {
			// TODO Auto-generated method stub
			dialog = ProgressDialog.show(Register.this, "", "Please wait");
		}
		
		@Override
		public void onApiResultOk(String result) {
			// TODO Auto-generated method stub
			fetcResponse(result);
			dialog.dismiss();
		}
	};
	
	public static void toRegister(Activity activity){
		Intent intent = new Intent(activity, Register.class);
		activity.startActivity(intent);
		
		BaseActivity.slideIn(activity);
	}
	
	protected void fetcResponse(String result) {
		// TODO Auto-generated method stub
		if (result.equals("")) {
			Utils.showMessage(Register.this, R.string.error_response);
		} else {
			try {
				JSONObject jsonObject = new JSONObject(result);
				if (jsonObject.getJSONObject("resultCode").getInt("code")==Constans.REGIS_SUCCESS) {
					preference.setUserId(jsonObject.getString(Constans.objectIdKey));
					ConfirmRegistration.toConfirmRegistration(Register.this);
					this.finish();
				} else {
					Utils.showMessage(Register.this, R.string.auth_failed);
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.d(Constans.APP_TAG, "REGISTER - ERROR IN FETCH RESPONSE : "+e.getMessage());
			}
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		BaseActivity.slideOut(Register.this);
	}
	
	private boolean isStillEmpty(String email, String firstName, 
			String lastName, String ipAddress) {
		// TODO Auto-generated method stub
		if (email.equals("")||firstName.equals("")||
				lastName.equals("")||ipAddress.equals("")) {
			return true;
		} else {
			return false;
		}
	}
}
