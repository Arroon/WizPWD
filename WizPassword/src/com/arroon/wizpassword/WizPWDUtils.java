package com.arroon.wizpassword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


public class WizPWDUtils {

	private final static String KEY_LOCK_PWD = "lock_pwd";

	private static Context mContext;

	private static SharedPreferences preference;

	public WizPWDUtils(Context context) {
		mContext = context;
		preference = PreferenceManager.getDefaultSharedPreferences(mContext);
	}

	public static List<Integer> stringToPassword(String string) {
		List<Integer> result = new ArrayList<Integer>();
		final byte[] bytes = string.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			result.add(Integer.valueOf(b));
		}
		return result;
	}

	
	public static String passwordToString(List<Integer> password) {
		if (password == null) {
			return "";
		}
		final int passwordSize = password.size();
		byte[] res = new byte[passwordSize];
		for (int i = 0; i < passwordSize; i++) {
			res[i] = (byte) ((int)password.get(i));
		}
		return Arrays.toString(res);
	}

	/**
	 * �����뱣�浽SharedPreferences,����������������������
	 * @param password
	 */
	public void savePassword(List<Integer> password) {
		Editor editor = preference.edit();
		editor.putString(KEY_LOCK_PWD, passwordToString(password));
		editor.commit();
	}

	/**
	 * ��SharedPreferences��ȡ����
	 * @return
	 */
	public String getPasswordString() {
		return preference.getString(KEY_LOCK_PWD, "");
	}

	/**
	 * ��ȡSharedPreferences��������뵱ǰ����Ƚ�
	 * @param password
	 * @return ����һ�·���1�����벻һ�·���0��
	 */
	@SuppressLint("NewApi")
	public int checkPassword(List<Integer> password) {
		String stored = getPasswordString();
		if (!stored.isEmpty()) {
			return stored.equals(passwordToString(password)) ? 1 : 0;
		}
		return -1;
	}

	/**
	 * ���SharedPreferences�������,��Ϊ���ַ�
	 */
	public void clearPassword() {
		savePassword(null);
	}

}
