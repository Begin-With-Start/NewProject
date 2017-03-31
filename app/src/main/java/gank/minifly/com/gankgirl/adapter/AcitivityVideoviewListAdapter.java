package gank.minifly.com.gankgirl.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.common.customer_widget.VideoFrameImageLoader;

public class AcitivityVideoviewListAdapter extends RecyclerView.Adapter<AcitivityVideoviewListAdapter.MyViewHolder> {

    private Context context;
    private VideoFrameImageLoader frameImageLoader;
    private boolean isFirst = true;
    private String [] videoUrls;
    private String [] videoNames;
    public AcitivityVideoviewListAdapter(Context context,VideoFrameImageLoader vfi,String [] videoNames) {
        this.context = context;
        this.frameImageLoader = vfi;
        videoUrls = frameImageLoader.getVideoUrls();
        this.videoNames = videoNames;
    }

    public void setVideoNames(String [] videoNames){
        this.videoNames = videoNames;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_videoview, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (isFirst) {
            frameImageLoader.initList();
            isFirst = false;
        }

        String mVideoUrl = videoUrls[position];
        String name = videoNames[position];

        holder.jcVideoPlayer.setTag(mVideoUrl);
        //从缓存中获取图片
        Bitmap bitmap = frameImageLoader.showCacheBitmap(VideoFrameImageLoader.formatVideoUrl(mVideoUrl));
        if (bitmap != null && mVideoUrl.equals(holder.jcVideoPlayer.getTag()) ) {
            holder.jcVideoPlayer.setImageBitmap(bitmap);
        } else {
            //没有从缓存中加载到时，先设置一张默认图
            holder.jcVideoPlayer.setImageResource(R.drawable.video_defult);
        }

        holder.titleTxt.setText(name);

        if(itemOclick!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    itemOclick.itemClick(position);
                }
            });
            holder.jcVideoPlayImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemOclick.itemClick(position);
                }
            });
            holder.jcVideoPlayImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemOclick.itemClick(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return videoUrls.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView jcVideoPlayer;
        TextView titleTxt;
        ImageView jcVideoPlayImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            jcVideoPlayImg = (ImageView) itemView.findViewById(R.id.video_gankview_play_img);
            titleTxt = (TextView) itemView.findViewById(R.id.video_gankview_title);
            jcVideoPlayer = (ImageView) itemView.findViewById(R.id.video_gankview_img);
        }
    }

    public interface ItemOclick{
        void itemClick(int position);
    }

    ItemOclick itemOclick;

    public void setItemOclick(ItemOclick itemOclick){
        this.itemOclick = itemOclick;
    }

}
