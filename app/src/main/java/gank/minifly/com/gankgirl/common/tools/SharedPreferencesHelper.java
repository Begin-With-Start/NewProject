package gank.minifly.com.gankgirl.common.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * author ：minifly
 * date: 2017/3/22
 * time: 13:30
 * desc: pf信息保存
 */
public class SharedPreferencesHelper {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    Context conext;

    public SharedPreferencesHelper(Context context) {
        this.conext = context;
        sp = context.getSharedPreferences("yuepai", 0);
        editor = sp.edit();
    }

    public void putValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getValue(String key) {
        // 第二个参数是默认值， 如果取的时候发现没数据，那就会自动设置为空
        return sp.getString(key, "");
    }

    public void putIntValue(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getIntValue(String key) {
        // 第二个参数是默认值， 如果取的时候发现没数据，那就会自动设置为空
        return sp.getInt(key, -1);
    }

    public void putBooleanValue(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBooleanValue(String key) {
        // 第二个参数是默认值， 如果取的时候发现没数据，那就会自动设置为空
        return sp.getBoolean(key, false);
    }

    public boolean getBooleanValue(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public void deleteValue(Context context, String key) {
        sp = context.getSharedPreferences("yuepai", 0);
        editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }
}
