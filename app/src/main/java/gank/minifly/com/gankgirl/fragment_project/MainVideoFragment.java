package gank.minifly.com.gankgirl.fragment_project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.adapter.AcitivityVideoviewListAdapter;
import gank.minifly.com.gankgirl.common.customer_widget.VideoFrameImageLoader;

/**
 * author ：minifly
 * date: 2017/3/24
 * time: 14:54
 * desc:
 */
public class MainVideoFragment extends BaseFragment {
    private View view;
    private Context mContext;

    private RecyclerView recyclerView;
    AcitivityVideoviewListAdapter adapterVideoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video_gank_view_list, container, false);
        initView();

        return view;
    }

    public void initView(){
        mContext = getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.video_gankeview_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        String [] videoUrls = {
                "http://video.jiecao.fm/8/17/bGQS3BQQWUYrlzP1K4Tg4Q__.mp4",
                "http://video.jiecao.fm/8/17/%E6%8A%AB%E8%90%A8.mp4",
                "http://video.jiecao.fm/8/18/%E5%A4%A7%E5%AD%A6.mp4",
                "http://video.jiecao.fm/8/16/%E8%B7%B3%E8%88%9E.mp4",
                "http://video.jiecao.fm/8/16/%E9%B8%AD%E5%AD%90.mp4",
                "http://video.jiecao.fm/8/16/%E9%A9%BC%E8%83%8C.mp4",
                "http://video.jiecao.fm/8/16/%E4%BF%AF%E5%8D%A7%E6%92%91.mp4",
                "http://video.jiecao.fm/5/1/%E8%87%AA%E5%8F%96%E5%85%B6%E8%BE%B1.mp4",
                "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4",
                "http://video.jiecao.fm/11/23/xu/%E5%A6%B9%E5%A6%B9.mp4"
        };

        VideoFrameImageLoader mVideoFrameImageLoader = new VideoFrameImageLoader(mContext, recyclerView, videoUrls);
        adapterVideoList = new AcitivityVideoviewListAdapter(mContext,mVideoFrameImageLoader);

        recyclerView.setAdapter(adapterVideoList);
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {//当这个控件滑动到屏幕之外的地方.
                if (JCVideoPlayerManager.getFirstFloor() != null) {
                    JCVideoPlayer videoPlayer = JCVideoPlayerManager.getFirstFloor();
                    if (((ViewGroup) view).indexOfChild(videoPlayer) != -1 && videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
                        JCVideoPlayer.releaseAllVideos();
                    }
                }
            }
        });

//        JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class, "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", "嫂子辛苦了");

    }

    @Override
    public void onStop() {
        super.onStop();
        if (JCVideoPlayer.backPress()) {
            return;
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
