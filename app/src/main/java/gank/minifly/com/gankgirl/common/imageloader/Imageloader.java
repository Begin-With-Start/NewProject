package gank.minifly.com.gankgirl.common.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * author ：minifly
 * date: 2017/3/16
 * time: 14:14
 * desc: 图片加载功能类 顶层定义类
 */
interface Imageloader {

    void displayImage(String url , ImageView view);
    void initLoader(Context mContext);
    void displayRoundImgPixel(String url, ImageView imgV,int roundPixel);
}
