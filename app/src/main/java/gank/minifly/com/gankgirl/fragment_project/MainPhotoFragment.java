package gank.minifly.com.gankgirl.fragment_project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gank.minifly.com.gankgirl.R;
import gank.minifly.com.gankgirl.fragment.BaseFragment;

/**
 * author ：minifly
 * date: 2017/3/10
 * time: 20:24
 * desc:
 */
public class MainPhotoFragment extends BaseFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_photolist, container, false);
        initView();
        return view;
    }

    public void initView(){

    }
}
