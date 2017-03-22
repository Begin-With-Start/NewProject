package gank.minifly.com.gankgirl;

import android.app.Application;
import android.graphics.Typeface;

import com.yolanda.nohttp.NoHttp;

import java.lang.reflect.Field;

import gank.minifly.com.gankgirl.common.tools.LogUtils;
import gank.minifly.com.gankgirl.common.tools.SharedPreferencesHelper;

/**
 * author ：minifly
 * date: 2017/3/10
 * time: 19:33
 * desc: 自定义的application
 */
public class MyApplication extends Application{

    static MyApplication instance = null;
    SharedPreferencesHelper sp;
    public static Typeface TypeFaceYaHei;//全局替换字体


    @Override
    public void onCreate() {
        super.onCreate();
        //实例
        instance = this;

        // 初始化NoHttp
        NoHttp.initialize(getApplicationContext());

        //sharepreferences的初始化.
        sp = new SharedPreferencesHelper(this);

        TypeFaceYaHei = Typeface.createFromAsset(getAssets(), "font/DroidSansFallback.ttf");

        try{
            Field field = Typeface.class.getDeclaredField("SERIF");
            field.setAccessible(true);
            field.set(null, TypeFaceYaHei);
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
            LogUtils.showErrLog("" + e.getMessage());
        }catch (IllegalAccessException e) {
            e.printStackTrace();
            LogUtils.showErrLog("" + e.getMessage());
        }
    }

    public static MyApplication getApplicationInstance() {
        return instance;
    }

}
