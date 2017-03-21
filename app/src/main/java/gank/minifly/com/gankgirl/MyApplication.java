package gank.minifly.com.gankgirl;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;

/**
 * author ：minifly
 * date: 2017/3/10
 * time: 19:33
 * desc: 自定义的application
 */
public class MyApplication extends Application{

    static MyApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // 初始化NoHttp
        NoHttp.initialize(getApplicationContext());
    }

    public static MyApplication getApplicationInstance() {
        return instance;
    }

}
