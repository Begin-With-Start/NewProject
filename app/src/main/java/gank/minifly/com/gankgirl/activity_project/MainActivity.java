package gank.minifly.com.gankgirl.activity_project;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.fragment_project.MainPhotoFragment;
import gank.minifly.com.gankgirl.imageloader.ImageloaderEngin;
import gank.minifly.com.gankgirl.imageloader.ImageloaderPoxyImp;

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
//        tabLayout.addTab(tabLayout.newTab().setText("tab1"));

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
                return new MainPhotoFragment();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "标题";
            }
        });


        tabLayout.setupWithViewPager(mViewPager);
    }
}
