package gank.minifly.com.gankgirl.imageloader;

/**
 * author ：minifly
 * date: 2017/3/16
 * time: 14:22
 * desc:
 */
interface  ImageloaderPoxy {

    <T extends Imageloader> T setLoader(T t);
}
