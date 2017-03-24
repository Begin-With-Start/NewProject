package gank.minifly.com.gankgirl.fragment_project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gank.minifly.com.gankgirl.R;

/**
 * author ：minifly
 * date: 2017/3/10
 * time: 20:24
 * desc:
 */
public class MainOtherFragment extends BaseFragment implements View.OnClickListener{

    private View view;
    private Context mContext;
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_other, container, false);
        initView();
        return view;
    }

    public void initView() {
        mContext = getContext();
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.main_activity_other_up_arrow_but);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_activity_other_up_arrow_but:
                break;
        }
    }
}
