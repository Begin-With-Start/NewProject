package gank.minifly.com.gankgirl.fragment_project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.adapter.PhotoAdapter;

/**
 * author ：minifly
 * date: 2017/3/10
 * time: 20:24
 * desc:
 */
public class MainPhotoFragment extends BaseFragment {

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


        photoFragmentRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        photoFragmentRecyclerview.setAdapter(new PhotoAdapter());
    }
}
