package gank.minifly.com.gankgirl.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.bean.ExcepressBean;
import gank.minifly.com.gankgirl.bean.RequestExpressBean;
import gank.minifly.com.gankgirl.common.http.OnLoadListener;
import gank.minifly.com.gankgirl.common.http.http_oo.NohttpEngin;
import gank.minifly.com.gankgirl.common.tools.LogUtils;
import gank.minifly.com.gankgirl.fragment.MainOtherFragment;
import gank.minifly.com.gankgirl.fragment.MainPhotoFragment;
import gank.minifly.com.gankgirl.fragment.MainVideoFragment;

public class MainActivity extends BaseActivity{

    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        init();
    }

    public void init(){


        tabLayout = (TabLayout) findViewById(R.id.main_tab_all_id);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager_id);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("资料总汇");
        //设置toolbar
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.addDrawerListener(toggle);//设置监听
        toggle.syncState();//加上同步
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        // 设置ViewPager的数据等
        setupViewPager();
        /**
         * JCVideoPlayerStandard.setUp("file:///sdcard/Wildlife.wmv", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"本地播放");
         */
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
                }else if(position == 1){
                    return new MainVideoFragment();
                }else{
                    return new MainOtherFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if(position==0){
                    return "妹纸图";
                }else if(position == 1){
                    return "视频";
                }else{
                    return "标题";
                }
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }


        if (JCVideoPlayer.backPress()) {//如果正在播放视频的话，先返回到列表页中.
            return;
        }

        // 如果没有的话就提示退出
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
            return;
        }
        finish();
        super.onBackPressed();

    }

}
