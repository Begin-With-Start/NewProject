package gank.minifly.com.gankgirl.activity_project;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.alibaba.fastjson.JSON;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.bean.ExcepressBean;
import gank.minifly.com.gankgirl.bean.RequestExpressBean;
import gank.minifly.com.gankgirl.fragment_project.MainOtherFragment;
import gank.minifly.com.gankgirl.fragment_project.MainPhotoFragment;
import gank.minifly.com.gankgirl.common.http.OnLoadListener;
import gank.minifly.com.gankgirl.common.http.http_oo.NohttpEngin;
import gank.minifly.com.gankgirl.common.tools.LogUtils;

public class MainActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    public void init(){
        tabLayout = (TabLayout) findViewById(R.id.main_tab_all_id);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager_id);


        // 设置ViewPager的数据等
        setupViewPager();

//        ImageloaderPoxyImp.getInstance().setLoader(new ImageloaderEngin()).displayImage("",new ImageView(mContext));
    }

    //设置viewpager
    public void setupViewPager(){
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                if(position==0){
                    return new MainPhotoFragment();
                }else{
                    return new MainOtherFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "标题";
            }
        });


        tabLayout.setupWithViewPager(mViewPager);
    }

    public void request() {
        String url = "http://apis.haoservice.com/weather";//type=yunda&postid=3959408859318

        RequestExpressBean requestData = new RequestExpressBean();
        requestData.setRequestUrl(url);
        requestData.setKey("8489e1b3f15b4b0c94646948195858c8");
        requestData.setPaybyvas(false);
        requestData.setCityname("杭州");

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
                        ExcepressBean resoureseBean = JSON.parseObject(response.get(), ExcepressBean.class);
                        LogUtils.showErrLog("结果:"+JSON.toJSON(resoureseBean) );
//                        if(resoureseBean.getMessage().equals("ok")){
//                            showToast("查询成功");
//                        }else{
//                            showToast(""+resoureseBean.getMessage());
//                        }
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
