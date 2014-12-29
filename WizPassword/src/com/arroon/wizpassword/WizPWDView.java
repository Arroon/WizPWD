package com.arroon.wizpassword;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 提取"为知笔记"Android九宫格密码控件
 * 
 * @website http://www.arroon.com
 * @author arroon
 * 
 */
public class WizPWDView extends FrameLayout implements OnClickListener {
	private ArrayList<Integer> passwordList = new ArrayList<Integer>();
	private OnPasswordListener mOnPasswordListener;
	private final int maxLength = 4;

	public WizPWDView(Context context) {
		super(context);
		init(context);
	}

	public WizPWDView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.view_password, this);
		findViewById(R.id.passwordKeyboard0).setOnClickListener(this);
		findViewById(R.id.passwordKeyboard1).setOnClickListener(this);
		findViewById(R.id.passwordKeyboard2).setOnClickListener(this);
		findViewById(R.id.passwordKeyboard3).setOnClickListener(this);
		findViewById(R.id.passwordKeyboard4).setOnClickListener(this);
		findViewById(R.id.passwordKeyboard5).setOnClickListener(this);
		findViewById(R.id.passwordKeyboard6).setOnClickListener(this);
		findViewById(R.id.passwordKeyboard7).setOnClickListener(this);
		findViewById(R.id.passwordKeyboard8).setOnClickListener(this);
		findViewById(R.id.passwordKeyboard9).setOnClickListener(this);
		findViewById(R.id.passwordKeyboardDel).setOnClickListener(this);
	}

	private void refreshPasswordView(int paramInt) {
		ImageView localImageView1 = (ImageView) findViewById(R.id.password0);
		ImageView localImageView2 = (ImageView) findViewById(R.id.password1);
		ImageView localImageView3 = (ImageView) findViewById(R.id.password2);
		ImageView localImageView4 = (ImageView) findViewById(R.id.password3);
		localImageView1.setImageResource(R.drawable.icon_password_null);
		localImageView2.setImageResource(R.drawable.icon_password_null);
		localImageView3.setImageResource(R.drawable.icon_password_null);
		localImageView4.setImageResource(R.drawable.icon_password_null);
		if (paramInt == 0)
			findViewById(R.id.passwordKeyboardDel)
					.setVisibility(View.INVISIBLE);
		if (paramInt > 0) {
			localImageView1.setImageResource(R.drawable.icon_password);
			findViewById(R.id.passwordKeyboardDel).setVisibility(View.VISIBLE);
		}
		if (paramInt > 1)
			localImageView2.setImageResource(R.drawable.icon_password);
		if (paramInt > 2)
			localImageView3.setImageResource(R.drawable.icon_password);
		if (paramInt > 3)
			localImageView4.setImageResource(R.drawable.icon_password);
		if (mOnPasswordListener != null) {
			mOnPasswordListener.onPasswordChanged(passwordList);
			if (passwordList.size() == maxLength)
				mOnPasswordListener.onPasswordCompleted(passwordList);
		}
	}

	@Override
	public void onClick(View v) {
		int vId = v.getId();
		if (vId == R.id.passwordKeyboard0) {
			this.add(0);
		} else if (vId == R.id.passwordKeyboard1) {
			this.add(1);
		} else if (vId == R.id.passwordKeyboard2) {
			this.add(2);
		} else if (vId == R.id.passwordKeyboard3) {
			this.add(3);
		} else if (vId == R.id.passwordKeyboard4) {
			this.add(4);
		} else if (vId == R.id.passwordKeyboard5) {
			this.add(5);
		} else if (vId == R.id.passwordKeyboard6) {
			this.add(6);
		} else if (vId == R.id.passwordKeyboard7) {
			this.add(7);
		} else if (vId == R.id.passwordKeyboard8) {
			this.add(8);
		} else if (vId == R.id.passwordKeyboard9) {
			this.add(9);
		} else if (vId == R.id.passwordKeyboardDel) {
			this.del();
		}
		// Android库工程中R.class中的资源常量不在为final类型
		// switch (v.getId()) {
		// case R.id.passwordKeyboard0:
		// this.add(0);
		// return;
		// case R.id.passwordKeyboard1:
		// this.add(1);
		// return;
		// case R.id.passwordKeyboard2:
		// this.add(2);
		// return;
		// case R.id.passwordKeyboard3:
		// this.add(3);
		// return;
		// case R.id.passwordKeyboard4:
		// this.add(4);
		// return;
		// case R.id.passwordKeyboard5:
		// this.add(5);
		// return;
		// case R.id.passwordKeyboard6:
		// this.add(6);
		// return;
		// case R.id.passwordKeyboard7:
		// this.add(7);
		// return;
		// case R.id.passwordKeyboard8:
		// this.add(8);
		// return;
		// case R.id.passwordKeyboard9:
		// this.add(9);
		// return;
		// case R.id.passwordKeyboardDel:
		// this.del();
		// default:
		// return;
		// }
	}

	public void add(int param) {
		if (passwordList.size() < maxLength) {
			passwordList.add(param);
		}
		refreshPasswordView(passwordList.size());
	}

	public void del() {
		if (passwordList.size() > 0) {
			passwordList.remove(passwordList.size() - 1);
		}
		refreshPasswordView(passwordList.size());
	}

	public void clear() {
		passwordList.clear();
		refreshPasswordView(0);
	}

	public void setTips(String tips) {
		((TextView) findViewById(R.id.passwordPrompt)).setText(tips);
	}

	public void setOnPasswordListener(OnPasswordListener onPasswordListener) {
		this.mOnPasswordListener = onPasswordListener;
	}

	public static interface OnPasswordListener {

		void onPasswordChanged(ArrayList<Integer> passwordList);

		void onPasswordCompleted(ArrayList<Integer> passwordList);
	}
}
