package gank.minifly.com.gankgirl.fragment_project;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

import gank.minifly.com.gankgirl.common.tools.ToastUtils;

/**
 * author ：minifly
 * date: 2017/3/10
 * time: 19:47
 * desc:
 */
public class BaseFragment extends Fragment {
    public RequestQueue requestQueue = null;

    public ToastUtils toastUtils;
    public Context mContext;
    private ProgressDialog progressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        try {
            requestQueue = NoHttp.newRequestQueue();
        }
        catch (Exception e) {

        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    @Override
    public void onDetach() {
        super.onDetach();
        requestQueue.cancelAll();
        requestQueue = null;
    }
    public void showToast(String key){
        toastUtils.showToast(key);
    }

    public void showDialog(String msg, DialogInterface.OnClickListener sureClickListener, DialogInterface.OnClickListener cancleClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
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
            progressDialog = new ProgressDialog(mContext);
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
