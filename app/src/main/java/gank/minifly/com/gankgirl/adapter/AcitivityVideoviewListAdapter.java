package gank.minifly.com.gankgirl.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.common.customer_widget.VideoFrameImageLoader;
import gank.minifly.com.gankgirl.common.tools.LogUtils;

public class AcitivityVideoviewListAdapter extends RecyclerView.Adapter<AcitivityVideoviewListAdapter.MyViewHolder> {

    private Context context;
    private VideoFrameImageLoader frameImageLoader;
    private boolean isFirst = true;
    private String [] videoUrls;
    public AcitivityVideoviewListAdapter(Context context,VideoFrameImageLoader vfi) {
        this.context = context;
        this.frameImageLoader = vfi;
        videoUrls = frameImageLoader.getVideoUrls();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_videoview, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (isFirst) {
            frameImageLoader.initList();
            isFirst = false;
        }

        String mVideoUrl = videoUrls[position];
        holder.jcVideoPlayer.thumbImageView.setTag(mVideoUrl);
        //从缓存中获取图片
        Bitmap bitmap = frameImageLoader.showCacheBitmap(VideoFrameImageLoader.formatVideoUrl(mVideoUrl));
        if (bitmap != null && mVideoUrl.equals(holder.jcVideoPlayer.thumbImageView.getTag()) ) {
            holder.jcVideoPlayer.thumbImageView.setImageBitmap(bitmap);
        } else {
            //没有从缓存中加载到时，先设置一张默认图
            holder.jcVideoPlayer.thumbImageView.setImageResource(R.drawable.video_defult);
        }

        holder.jcVideoPlayer.setUp(
                mVideoUrl, JCVideoPlayer.SCREEN_LAYOUT_LIST,
                "都用一个先吧");


        //截取所有的请求直接在新的页面进行播放.
        holder.jcVideoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.showErrLog("点击了...视频的东西.");
                JCVideoPlayerStandard.startFullscreen(context, JCVideoPlayerStandard.class, "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", "嫂子辛苦了");
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoUrls.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        JCVideoPlayerStandard jcVideoPlayer;
        LinearLayout jcVideoLin;

        public MyViewHolder(View itemView) {
            super(itemView);
            jcVideoLin = (LinearLayout) itemView.findViewById(R.id.video_gankview_video_lin);
            jcVideoPlayer = (JCVideoPlayerStandard) itemView.findViewById(R.id.video_gankview_videoplayer);
        }
    }

}
