package gank.minifly.com.gankgirl.activity_project;

import android.os.Bundle;
import android.view.View;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.common.imageloader.ImageloaderEngin;
import gank.minifly.com.gankgirl.common.imageloader.ImageloaderPoxyImp;

public class VideoGankViewDetailActivity extends BaseActivity implements View.OnClickListener{
    private JCVideoPlayerStandard mJcVideoPlayerStandard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_gank_view_detail);
        initView();
    }

    public void initView(){
        Bundle bundle  = getIntent().getExtras();

        if(bundle!=null && bundle.containsKey("")){

        }

        mJcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.gank_video_jc_video);
        mJcVideoPlayerStandard.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4",JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子不信");
        ImageloaderPoxyImp.getInstance().setLoader(new ImageloaderEngin(mContext)).displayImage("http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360",mJcVideoPlayerStandard.thumbImageView);

        mJcVideoPlayerStandard.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gank_video_jc_video:
                JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class, "http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4", "嫂子辛苦了");
                break;
        }
    }
}
