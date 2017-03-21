package gank.minifly.com.gankgirl.fragment_project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.List;

import gank.minifly.com.gankgirl.constant.UrlConstant;
import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.adapter.PhotoAdapter;
import gank.minifly.com.gankgirl.adapter.SpacesItemDecoration;
import gank.minifly.com.gankgirl.bean.FuliRequestBean;
import gank.minifly.com.gankgirl.bean.FuliResponseBean;
import gank.minifly.com.gankgirl.common.http.OnLoadListener;
import gank.minifly.com.gankgirl.common.http.http_oo.NohttpEngin;
import gank.minifly.com.gankgirl.common.tools.LogUtils;

/**
 * author ：minifly
 * date: 2017/3/10
 * time: 20:24
 * desc:
 */
public class MainOtherFragment extends BaseFragment {

    private View view;
    private Context mContext;
    private RecyclerView photoFragmentRecyclerview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_photolist, container, false);
        initView();
        return view;
    }

    public void initView() {
        mContext = getContext();

        photoFragmentRecyclerview = (RecyclerView) view.findViewById(R.id.photo_fragment_recyclerview);

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
            LogUtils.showErrLog("" + response.toString());
            switch (what) {

                case 1:
                    try {
                        FuliResponseBean resoureseBean = JSON.parseObject(response.get(), FuliResponseBean.class);
                        if(!resoureseBean.isError()){
                            List<FuliResponseBean.ResultsBean> list= resoureseBean.getResults();
                            photoFragmentRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                            //设置item之间的间隔
                            SpacesItemDecoration decoration=new SpacesItemDecoration(16);
                            photoFragmentRecyclerview.addItemDecoration(decoration);

                            photoFragmentRecyclerview.setAdapter(new PhotoAdapter(mContext,list));

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
            hideProgressDialog();
        }

        @Override
        public void onStart(int what) {
            super.onStart(what);
            showProgressDialog();
        }
    };

}
