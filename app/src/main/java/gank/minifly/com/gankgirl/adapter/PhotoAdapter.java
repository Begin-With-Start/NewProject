package gank.minifly.com.gankgirl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.bean.FuliResponseBean;
import gank.minifly.com.gankgirl.imageloader.ImageloaderEngin;
import gank.minifly.com.gankgirl.imageloader.ImageloaderPoxyImp;
import gank.minifly.com.gankgirl.tools.ScreenUtils;


/**
 * author ：minifly
 * date: 2017/3/13
 * time: 17:01
 * desc:
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{
    private List<FuliResponseBean.ResultsBean> list;
    private Context mContext;


    public PhotoAdapter(Context mContext,List<FuliResponseBean.ResultsBean> list){
        this.mContext = mContext;
        this.list = list;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.adapter_mainphoto_item,parent,false);
        return new ViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FuliResponseBean.ResultsBean bean = list.get(position);

        ImageloaderPoxyImp.getInstance().setLoader(new ImageloaderEngin()).displayImage(bean.getUrl()+"?imageView2/0/w/"+ ScreenUtils.getScreenWidth(mContext)/2, holder.imageView);

    }

    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }else{
            return list.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            //为ViewHolder中的组件进行赋值
            imageView = (ImageView) itemView.findViewById(R.id.adapter_mainphoto_img);
        }
    }
}
