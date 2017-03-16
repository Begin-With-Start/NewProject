package gank.minifly.com.gankgirl.imageloader;

/**
 * author ：minifly
 * date: 2017/3/16
 * time: 14:22
 * desc: 具体代理类.
 */
public class ImageloaderPoxyImp implements ImageloaderPoxy{
    public static ImageloaderPoxyImp imageLoaderpoxy;

    @Override
    public <T extends Imageloader> T setLoader(T t) {
        return t;
    }

    public  static  ImageloaderPoxyImp  getInstance() {
        if(imageLoaderpoxy==null){
            imageLoaderpoxy = new ImageloaderPoxyImp();
        }
        return imageLoaderpoxy;
    }

}
