package gank.minifly.com.gankgirl.common.tools;

import org.json.JSONObject;

/**
 * Created by ZFY on 2016/10/20 0020.
 */

public class JsonUtil {
	public static String getCode(String responseBody) {
		JSONObject json = null;
		String code = null;
		try {
			json = new JSONObject(responseBody);
			code = json.optString("code");
		} catch (Exception e) {
			ToastUtils.showToast("JsonUtil: " + e.getMessage());
			e.printStackTrace();
		}
		return code;
	}


	public static String getMsg(String responseBody) {
		JSONObject json = null;
		String msg = "后端没有返回msg";
		try {
			json = new JSONObject(responseBody);
			msg = json.optString("msg");
			return msg;
		} catch (Exception e) {
			ToastUtils.showToast("JsonUtil: " + e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}


	public static String getData(String responseBody) {
		JSONObject json = null;
		String data = null;
		try {
			json = new JSONObject(responseBody);
			data = json.optString("data");
		} catch (Exception e) {
			ToastUtils.showToast("JsonUtil: " + e.getMessage());
			e.printStackTrace();
		}
		return data;
	}
}
