package gank.minifly.com.gankgirl.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.common.tools.ToastUtils;

public class BaseActivity extends AppCompatActivity {
    public RequestQueue requestQueue;
    public ToastUtils toastUtils;
    public Activity mActivity;
    public Context mContext;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        mContext = this;

        requestQueue = NoHttp.newRequestQueue();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll();
    }

    public void showToast(String key){
        toastUtils.showToast(key);
    }

    public void showDialog(String msg, DialogInterface.OnClickListener sureClickListener,DialogInterface.OnClickListener cancleClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMessage(msg);
        builder.setPositiveButton("确定", sureClickListener == null ? new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        } : sureClickListener).setPositiveButton("",cancleClickListener==null?new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        }:cancleClickListener);

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
    }


    // 显示ProgressDialog
    public void showProgressDialog(String ... text) {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new ProgressDialog(mActivity);
            if(text==null){//当参数没有给的时候
                progressDialog.setMessage("请求中.");
            }else if(text.length>0){//当给了一个参数的时候.
                progressDialog.setMessage(text[0]);
            }
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }
    //隐藏progress
    public void hideProgressDialog(){
        if (progressDialog == null || progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


}
