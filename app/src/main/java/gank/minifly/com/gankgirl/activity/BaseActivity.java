package gank.minifly.com.gankgirl.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

import gank.minifly.com.gankgirl.R;

public class BaseActivity extends AppCompatActivity {
    public RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = NoHttp.newRequestQueue();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll();
    }
}
