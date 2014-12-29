package com.arroon.testwizpassword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

import com.arroon.wizpassword.WizPWDUtils;

public class MainActivity extends Activity {

	final static int TURN_OFF = 1;
	final static int TURN_ON = 2;
	final static int CHECK = 3;
	String localPwd;
	WizPWDUtils wizPWDUtils;
	CheckBox cb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		wizPWDUtils = new WizPWDUtils(MainActivity.this);
		localPwd = wizPWDUtils.getPasswordString();
		cb = (CheckBox) findViewById(R.id.cb);

		if (localPwd.length() > 0) {
			Intent intent = new Intent(MainActivity.this, PasswordActivity.class);
			intent.putExtra("action", CHECK);
			startActivity(intent);
		}

		cb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, PasswordActivity.class);
				if (localPwd.length() > 0) {
					intent.putExtra("action", TURN_OFF);
				} else {
					intent.putExtra("action", TURN_ON);
				}
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		localPwd = wizPWDUtils.getPasswordString();
		if (localPwd.length() > 0) {
			cb.setChecked(true);
		} else {
			cb.setChecked(false);
		}
	}
}