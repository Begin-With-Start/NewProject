package gank.minifly.com.gankgirl.fragment_project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.activity_project.PhotoGankViewdetailActivity;
import gank.minifly.com.gankgirl.adapter.PhotoAdapter;
import gank.minifly.com.gankgirl.adapter.SpacesItemDecoration;
import gank.minifly.com.gankgirl.bean.FuliRequestBean;
import gank.minifly.com.gankgirl.bean.FuliResponseBean;
import gank.minifly.com.gankgirl.common.http.OnLoadListener;
import gank.minifly.com.gankgirl.common.http.http_oo.NohttpEngin;
import gank.minifly.com.gankgirl.common.tools.LogUtils;
import gank.minifly.com.gankgirl.constant.UrlConstant;

/**
 * author ：minifly
 * date: 2017/3/10
 * time: 20:24
 * desc:
 */
public class MainPhotoFragment extends BaseFragment implements View.OnClickListener{

    private View view;
    private Context mContext;
    private RecyclerView photoFragmentRecyclerview;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private FloatingActionButton upArrowBtn;


    private PhotoAdapter adapter;
    private List<FuliResponseBean.ResultsBean> currentPhotoList = new LinkedList<>();
    private int currentPageNum = 1;
    private StaggeredGridLayoutManager myLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_photolist, container, false);
        initView();
        return view;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_activity_up_arrow_but:
                photoFragmentRecyclerview.scrollToPosition(currentPhotoList.size()/2);
                photoFragmentRecyclerview.smoothScrollToPosition(0);
                break;

        }
    }

    public void initView() {
        mContext = getContext();

        photoFragmentRecyclerview = (RecyclerView) view.findViewById(R.id.photo_fragment_recyclerview);
        mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.photo_fragment_swipe_refresh_layout);
        upArrowBtn = (FloatingActionButton) view.findViewById(R.id.main_activity_up_arrow_but);

        upArrowBtn.setOnClickListener(this);

        myLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        myLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        photoFragmentRecyclerview.setLayoutManager(myLayoutManager);
        photoFragmentRecyclerview.addItemDecoration(new SpacesItemDecoration(2, 16, true));

        adapter = new PhotoAdapter(mContext, currentPhotoList);
        adapter.setItemOnclicker(new PhotoAdapter.ItemOnclicker() {
            @Override
            public void itemOnclick(FuliResponseBean.ResultsBean bean) {
                Bundle bundle = new Bundle();
                FuliResponseBean tempBean = new FuliResponseBean();
                List<FuliResponseBean.ResultsBean> list = tempBean.getResults();
                if(list==null){
                    list = new LinkedList<>();
                }
                list.add(bean);
                tempBean.setResults(list);

                bundle.putParcelable("bean",tempBean);

                startActivity(PhotoGankViewdetailActivity.class,bundle);
            }
        });
        photoFragmentRecyclerview.setAdapter(adapter);

        if(currentPhotoList==null || currentPhotoList.size()==0){
            request("" + currentPageNum);
            mySwipeRefreshLayout.setRefreshing(true);
        }



        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                request("" + currentPageNum);
                mySwipeRefreshLayout.setRefreshing(false);
            }
        });


        photoFragmentRecyclerview.setOnScrollListener(new RecyclerView.OnScrollListener(){
            //用来标记是否正在向最后一个滑动，既是否向下滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                myLayoutManager.invalidateSpanAssignments();//为了防止快速滑动的空白问题.

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int[] lastVisiblePositions = manager.findLastVisibleItemPositions(new int[manager.getSpanCount()]);
                    int lastVisiblePos = getMaxElem(lastVisiblePositions);
                    int totalItemCount = manager.getItemCount();


                    // 判断是否滚动到底部
                    if (lastVisiblePos == (totalItemCount -1) && isSlidingToLast) {
                        //加载更多功能的代码
                        request("" + currentPageNum);
                        mySwipeRefreshLayout.setRefreshing(true);
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
    }


    public void request(String pageNum) {

        String url = UrlConstant.FULI_URL + "/" + pageNum;// /1来取第几页

        FuliRequestBean requestData = new FuliRequestBean();
        requestData.setRequestUrl(url);

        Request<String> request = NohttpEngin.getRequest(requestData,NohttpEngin.METHOD_GET);

        requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(1, request, onResponseListener);
    }


    OnResponseListener<String> onResponseListener = new OnLoadListener<String>() {

        @Override
        public void onSuccess(int what, Response<String> response) {
//            LogUtils.showErrLog("" + response.get());
            switch (what) {
                case 1:
                    try {
                        FuliResponseBean resoureseBean = JSON.parseObject(response.get(), FuliResponseBean.class);
                        if(!resoureseBean.isError()){
                            currentPageNum ++ ;
                            currentPhotoList.addAll(resoureseBean.getResults());
                            LogUtils.showErrLog("总计" + currentPhotoList.size());
                            //设置item之间的间隔
                            adapter.setList(currentPhotoList);
                        }else{
                            showToast("查询失败");
                        }
                    } catch (Exception e) {
                        showToast("数据解析异常");
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
            mySwipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onStart(int what) {
            super.onStart(what);
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
}
