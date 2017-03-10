package gank.minifly.com.gankgirl.http;

import android.app.Activity;

import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Response;

import gank.minifly.com.gankgirl.tools.JsonUtil;
import gank.minifly.com.gankgirl.tools.LogUtils;
import gank.minifly.com.gankgirl.tools.ToastUtils;

/**
 * Created by ZFY on 2016/10/19 0019.
 */

public abstract class WrappedListener implements OnResponseListener<String> {


	//	在onStart()和onFinish里面显示和隐藏进度条

	public abstract void onSuccess(int what, String responseBody);

	public abstract void onFail(int what, String error);

	private Activity mActivity;

	public WrappedListener(Activity activity) {
		mActivity = activity;
	}

	public WrappedListener() {
	}

	@Override
	public void onSucceed(int what, Response<String> response) {
		String responseBody = response.get();
		int httpCode = response.responseCode();
		LogUtils.showInfoLog("请求到的参数 " + "httpCode:" + httpCode + ", body:" + responseBody);
		if (httpCode == 200) {
			String code = JsonUtil.getCode(responseBody);
		} else {
			ToastUtils.showToast(httpCode + "--->" + responseBody);
		}
	}


	@Override
	public void onFailed(int what, Response<String> response) {
		String error = response.getException().getMessage();
		LogUtils.showInfoLog("请求后的错误:" +  error);
		ToastUtils.showToast(error);
		onFail(what, error);
	}

}
