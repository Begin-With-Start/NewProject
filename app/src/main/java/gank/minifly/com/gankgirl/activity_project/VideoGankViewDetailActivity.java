package gank.minifly.com.gankgirl.activity_project;

import android.os.Bundle;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.common.imageloader.ImageloaderEngin;
import gank.minifly.com.gankgirl.common.imageloader.ImageloaderPoxyImp;

public class VideoGankViewDetailActivity extends BaseActivity {
    private JCVideoPlayerStandard mJcVideoPlayerStandard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_gank_view_detail);
        initView();
    }

    public void initView(){

        mJcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.gank_video_jc_video);
        mJcVideoPlayerStandard.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4",JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子不信");
        ImageloaderPoxyImp.getInstance().setLoader(new ImageloaderEngin(mContext)).displayImage("http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360",mJcVideoPlayerStandard.thumbImageView);

    }


    //JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class, "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", "嫂子辛苦了");
}
