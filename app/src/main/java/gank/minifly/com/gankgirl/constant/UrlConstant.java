package gank.minifly.com.gankgirl.constant;

/**
 * author ：minifly
 * date: 2017/3/20
 * time: 18:43
 * desc:
 */
public class UrlConstant {
    /**
     * http://gank.io/api/data/Android/10/1
     http://gank.io/api/data/福利/10/1
     http://gank.io/api/data/iOS/20/2
     http://gank.io/api/data/all/20/2
     */
    public static final String GANK_BASE_URL = "http://www.gank.io/api/";
    public static final String FULI_URL = GANK_BASE_URL + "data/%E7%A6%8F%E5%88%A9/10";// /1来取第几页

    

    public static final String MY_SERVER_BASE_URL = "http://115.181.89.102/DataServer/";

    /**
     * 获取主页的信息
     */
    public static final String MAINPAGE_URL_IMAGE = MY_SERVER_BASE_URL + "info/getMainPage";

    /**
     * 获取视频信息
     */
    public static final String MAIN_VIDEO_URL = MY_SERVER_BASE_URL + "meizhi/getMeizhiVideo";

}
