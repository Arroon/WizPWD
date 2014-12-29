package com.arroon.testwizpassword;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.arroon.wizpassword.WizPWDUtils;
import com.arroon.wizpassword.WizPWDView;
import com.arroon.wizpassword.WizPWDView.OnPasswordListener;

public class PasswordActivity extends Activity {

	WizPWDView wizPWDView;
	WizPWDUtils wizPWDUtils;
	int action;

	boolean isHalf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȫ��
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		action = this.getIntent().getExtras().getInt("action");

		wizPWDView = new WizPWDView(PasswordActivity.this);
		wizPWDUtils = new WizPWDUtils(PasswordActivity.this);

		wizPWDView.setOnPasswordListener(new OnPasswordListener() {

			@Override
			public void onPasswordCompleted(ArrayList<Integer> passwordList) {
				switch (action) {
				case MainActivity.CHECK:
					if (1 == wizPWDUtils.checkPassword(passwordList)) {
						finish();// �����ɹ�
					} else {
						wizPWDView.clear();
						Toast.makeText(PasswordActivity.this, "�������",
								Toast.LENGTH_SHORT).show();
					}
					break;
				case MainActivity.TURN_ON:
					if (!isHalf) {// ���һ������
						//
						wizPWDUtils.savePassword(passwordList);

						wizPWDView.clear();
						wizPWDView.setTips("���ٴ���������");

						isHalf = true;

					} else {// ��ڶ�������
						if (1 == wizPWDUtils.checkPassword(passwordList)) {// ����ƥ��

							Toast.makeText(PasswordActivity.this, "�����Ѽ�¼",
									Toast.LENGTH_SHORT).show();
							isHalf = false;
							finish();
						} else {// ���벻ƥ��

							wizPWDUtils.clearPassword();

							wizPWDView.clear();
							wizPWDView.setTips("����������");
							Toast.makeText(PasswordActivity.this,
									"�������벻һ�£�����������", Toast.LENGTH_SHORT).show();
							isHalf = false;
						}
					}
					break;
				case MainActivity.TURN_OFF:
					if (1 == wizPWDUtils.checkPassword(passwordList)) {
						wizPWDUtils.clearPassword();
						Toast.makeText(PasswordActivity.this, "�����ѹر�",
								Toast.LENGTH_SHORT).show();
						finish();
					} else {
						wizPWDView.clear();
						Toast.makeText(PasswordActivity.this, "�������",
								Toast.LENGTH_SHORT).show();
					}
					break;
				default:
					break;
				}
			}

			@Override
			public void onPasswordChanged(ArrayList<Integer> passwordList) {
			}
		});
		setContentView(wizPWDView);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (MainActivity.CHECK == action) {
				// �����ؼ��˳�Ӧ��
				Intent startMain = new Intent(Intent.ACTION_MAIN);
				startMain.addCategory(Intent.CATEGORY_HOME);
				startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(startMain);
				System.exit(0);
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (isHalf)
			wizPWDUtils.clearPassword();
	}
}
