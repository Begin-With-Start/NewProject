package gank.minifly.com.gankgirl.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.adapter.AcitivityVideoviewListAdapter;
import gank.minifly.com.gankgirl.bean.FuliRequestBean;
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
    private SwipeRefreshLayout swipeRefreshLayout;
    private AcitivityVideoviewListAdapter adapterVideoList;


    private String[] videoUrls;
    private String[] names;
    private List<String> videoStrList = new LinkedList<>();
    private List<String> nameStrList = new LinkedList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video_gank_view_list, container, false);
        initView();

        return view;
    }

    public void initView(){
        mContext = getContext();

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.photo_fragment_swipe_refresh_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.video_gankeview_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        final String [] videoUrls = {
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
        final String [] names = {
                "先用这个吧",
                "先用这个吧",
                "先用这个吧",
                "先用这个吧",
                "先用这个吧",
                "先用这个吧",
                "先用这个吧",
                "先用这个吧",
                "先用这个吧",
                "先用这个吧"
        };

        VideoFrameImageLoader mVideoFrameImageLoader = new VideoFrameImageLoader(mContext, recyclerView, videoUrls);
        adapterVideoList = new AcitivityVideoviewListAdapter(mContext,mVideoFrameImageLoader,names);

        adapterVideoList.setItemOclick(new AcitivityVideoviewListAdapter.ItemOclick() {
            @Override
            public void itemClick(int position) {
                LogUtils.showErrLog("点击事件");
                JCVideoPlayerStandard.startFullscreen(mContext, JCVideoPlayerStandard.class, videoUrls[position], names[position]);
            }
        });

        recyclerView.setAdapter(adapterVideoList);
        //视频滑动到屏幕外，直接将所有的视频停止.
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

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener(){
            //用来标记是否正在向最后一个滑动，既是否向下滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisiblePos = manager.findLastVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部
                    if (lastVisiblePos == (totalItemCount -1) && isSlidingToLast) {
                        //加载更多功能的代码
                        showToast("滚动到底部了.");
//                        request("" + currentPageNum);
//                        mySwipeRefreshLayout.setRefreshing(true);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if(dy > 0){
                    //大于0表示，正在向下滚动
                    isSlidingToLast = true;
                }else{
                    //小于等于0 表示停止或向上滚动
                    isSlidingToLast = false;
                }

            }
        });

//        request(currentPage+"");
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
                        FuliVideoBean resoureseBean = JSON.parseObject(response.get(), FuliVideoBean.class);

                        if(resoureseBean.getCode().equals("0")){
                            for(FuliVideoBean.Data bean : resoureseBean.getData()){
                                videoStrList.add(bean.getUrl());//每次的请求都向list中添加新的数据.
                                nameStrList.add(bean.getDescription().substring(0,15));
                            }
                            videoUrls = new String[resoureseBean.getData().size()];
                            names = new String[resoureseBean.getData().size()];

                            videoStrList.toArray(videoUrls);
                            nameStrList.toArray(names);

                            VideoFrameImageLoader mVideoFrameImageLoader = new VideoFrameImageLoader(mContext, recyclerView, videoUrls);
                            adapterVideoList = new AcitivityVideoviewListAdapter(mContext,mVideoFrameImageLoader,names);
                            adapterVideoList.notifyDataSetChanged();

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
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onStart(int what) {
            super.onStart(what);
            showProgressDialog("请求中.");
            swipeRefreshLayout.setRefreshing(true);

        }
    };

    private int getMaxElem(int[] arr) {
        int size = arr.length;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (arr[i]>maxVal)
                maxVal = arr[i];
        }
        return maxVal;
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

    @Override
    public void onResume() {
        super.onResume();
    }
}