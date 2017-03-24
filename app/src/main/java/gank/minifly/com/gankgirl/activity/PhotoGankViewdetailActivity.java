package gank.minifly.com.gankgirl.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;

import java.util.List;

import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.bean.FuliResponseBean;
import gank.minifly.com.gankgirl.common.imageloader.ImageloaderEngin;
import gank.minifly.com.gankgirl.common.imageloader.ImageloaderPoxyImp;
import gank.minifly.com.gankgirl.common.tools.ScreenUtils;

public class PhotoGankViewdetailActivity extends BaseActivity {
    private ViewPager myViewPager;
    private TextView countTxt;
    private Context mContext;
    private  FuliResponseBean allBean = new FuliResponseBean();

    private int imageWidth;
    private List<FuliResponseBean.ResultsBean> list;
    private int allCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gank_viewdetail);
        initView();
    }

    public void initView(){
        myViewPager = (ViewPager)findViewById(R.id.photo_gank_view_pager);
        countTxt = (TextView)findViewById(R.id.photo_gank_count_txt);

        mContext = this;
        Bundle bundle = getIntent().getExtras();
        if(bundle.containsKey("bean")){
            allBean = bundle.getParcelable("bean");
            if(allBean!=null){
                list = allBean.getResults();
            }
        }

        imageWidth = ScreenUtils.getScreenWidth(mContext);
        allCount = list.size();
        countTxt.setText("" + 1 +"/" + allCount);

        myViewPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));

        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                countTxt.setText("" + (position+1)+"/" + allCount);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        myViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                if(list==null){
                    return 0;
                }else{
                    return list.size();
                }
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                FuliResponseBean.ResultsBean bean = list.get(position);
                PhotoView view = new PhotoView(PhotoGankViewdetailActivity.this);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ImageloaderPoxyImp.getInstance().setLoader(new ImageloaderEngin(mContext)).displayImage(bean.getUrl(),view);//+"?imageView2/0/w/"+ imageWidth
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }

}
