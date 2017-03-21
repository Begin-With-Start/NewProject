package gank.minifly.com.gankgirl.common.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * author ：minifly
 * date: 2017/3/16
 * time: 14:14
 * desc: 图片加载功能类
 */
public class ImageloaderEngin implements Imageloader{

    DisplayImageOptions options;
    DisplayImageOptions roundOptions;
    ImageLoader imageLoader = null;
    ImageLoader roundImageLoader = null;

    public ImageloaderEngin(Context mContext){
        imageLoader = ImageLoader.getInstance();
        roundImageLoader = ImageLoader.getInstance();

        if (!imageLoader.isInited()) {//初始化之后就不用初始化了
            initLoader(mContext);
        }
    }

    @Override
    public void initLoader(Context mContext) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                // 设置内存缓存大小
                .threadPriority(Thread.NORM_PRIORITY)
                // 设置线程优先级
                .diskCacheSize(200 * 1024 * 1024).memoryCacheSize(500 * 1024 * 1024)
                .denyCacheImageMultipleSizesInMemory().threadPoolSize(4).tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new WeakMemoryCache()).memoryCacheSizePercentage(60)
                .build();

        imageLoader.init(config);

        roundImageLoader.init(config);
    }

    @Override
    public void displayImage(String url, ImageView view) {
        try {
            options = new DisplayImageOptions.Builder().cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                    .cacheInMemory(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565).build();
            imageLoader.displayImage(url, view, options);
        } catch (Exception e) {

        }
    }


    // 显示圆角图片 指定圆角角度
    @Override
    public void displayRoundImgPixel(String url, ImageView imgV,int roundPixel) {
        try {

            roundOptions = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(roundPixel)) // 设置成圆角图片
                    .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                    .cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY).build();
            roundImageLoader.displayImage(url, imgV, roundOptions);
        } catch (Exception e) {

        }
    }
}
