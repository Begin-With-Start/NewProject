package gank.minifly.com.gankgirl.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.LinkedList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.adapter.AcitivityVideoviewListAdapter;
import gank.minifly.com.gankgirl.bean.FuliRequestBean;
import gank.minifly.com.gankgirl.bean.FuliResponseBean;
import gank.minifly.com.gankgirl.bean.FuliVideoBean;
import gank.minifly.com.gankgirl.common.customer_widget.VideoFrameImageLoader;
import gank.minifly.com.gankgirl.common.http.OnLoadListener;
import gank.minifly.com.gankgirl.common.http.http_oo.NohttpEngin;
import gank.minifly.com.gankgirl.common.tools.LogUtils;
import gank.minifly.com.gankgirl.constant.UrlConstant;

/**
 * author ：minifly
 * date: 2017/3/24
 * time: 14:54
 * desc:
 */
public class MainVideoFragment extends BaseFragment {
    private View view;
    private Context mContext;
    private int currentPage = 0;
    private RecyclerView recyclerView;
    AcitivityVideoviewListAdapter adapterVideoList;
    private String[] videoUrls;
    private String[] names;

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

//        JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class, "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", "嫂子辛苦了");
        request(currentPage+"");
    }
    public void request(String pageNum) {
        String url = UrlConstant.MAIN_VIDEO_URL;// /1来取第几页
        FuliRequestBean requestData = new FuliRequestBean();
        requestData.setPage("" + pageNum);
        requestData.setRequestUrl(url);
        Request<String> request = NohttpEngin.getRequest(requestData,NohttpEngin.METHOD_GET);
        requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(1, request, onResponseListener);
    }


    OnResponseListener<String> onResponseListener = new OnLoadListener<String>() {

        @Override
        public void onSuccess(int what, Response<String> response) {
            LogUtils.showErrLog("" + response.get());
            currentPage++;
            switch (what) {
                case 1:
                    try {
                        List<String > videoList = new LinkedList<>();
                        List<String > nameList = new LinkedList<>();
                        videoList.toArray();
                        FuliVideoBean resoureseBean = JSON.parseObject(response.get(), FuliVideoBean.class);
                        if(resoureseBean.getCode().equals("0")){
                            for(FuliVideoBean.Data bean : resoureseBean.getData()){
                                videoList.add(bean.getUrl());
                                nameList.add(bean.getDescription().substring(0,15));
                            }
                            videoUrls = new String[resoureseBean.getData().size()];
                            names = new String[resoureseBean.getData().size()];

                            videoList.toArray(videoUrls);
                            nameList.toArray(names);

                            VideoFrameImageLoader mVideoFrameImageLoader = new VideoFrameImageLoader(mContext, recyclerView, videoUrls);
                            adapterVideoList = new AcitivityVideoviewListAdapter(mContext,mVideoFrameImageLoader,names);

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
                        }else{
                            showToast("查询失败");
                        }
                    } catch (Exception e) {
                        LogUtils.showErrLog(""+e.getMessage());
                        showToast("数据解析异常" +e.getMessage());
                    }
                    break;

            }


        }

        @Override
        public void onError(int what, Response<String> response) {

        }

        @Override
        public void onFinish(int what) {
            super.onFinish(what);
            hideProgressDialog();
        }

        @Override
        public void onStart(int what) {
            super.onStart(what);
            showProgressDialog("请求中.");
        }
    };
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
