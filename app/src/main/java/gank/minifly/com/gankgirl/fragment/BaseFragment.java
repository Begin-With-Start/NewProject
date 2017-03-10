package gank.minifly.com.gankgirl.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

/**
 * author ：minifly
 * date: 2017/3/10
 * time: 19:47
 * desc:
 */
public class BaseFragment extends Fragment {
    public RequestQueue requestQueue = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            requestQueue = NoHttp.newRequestQueue();
        }
        catch (Exception e) {

        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    @Override
    public void onDetach() {
        super.onDetach();
        requestQueue.cancelAll();
        requestQueue = null;
    }

}
