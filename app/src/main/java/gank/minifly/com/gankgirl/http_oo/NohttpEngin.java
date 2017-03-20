package gank.minifly.com.gankgirl.http_oo;

import com.alibaba.fastjson.JSON;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

import java.lang.reflect.Field;
import java.util.List;

import gank.minifly.com.gankgirl.tools.LogUtils;

/**
 * author ：minifly
 * date: 2017/3/20
 * time: 14:25
 * desc:
 */
public class NohttpEngin {
    // 请求方式
    protected static int mMethod ;

    public static final int METHOD_POST = 1;

    public static final int METHOD_GET = 2;

    /**
     * 请求
     */
    public static Request<String> getRequest(IRequestData requestdata,int ... method){
        HttpRequestData jsonRequest = (HttpRequestData) requestdata;
        String url = jsonRequest.getRequestUrl();
        // 创建请求对象。
        Request<String> request = null;

        LogUtils.showInfoLog("url地址: " + url);
        if (method.length != 0) {
            mMethod = method[0];
        }

        try {
            List<Field> fields = DataParser.getFields(jsonRequest.getClass(), HttpRequestData.class);

            if (mMethod == METHOD_POST) {//全按照post来进行.
                request = NoHttp.createStringRequest(url, RequestMethod.POST);
                if (requestdata != null) {
                    //先改回来，冯辉的代码会出问题，跟董杰沟通，需要分开处理
                    String jsonStr = JSON.toJSONString(requestdata);
                    LogUtils.showInfoLog("提交的参数: " + jsonStr);
                    request.setDefineRequestBodyForJson(jsonStr);
                }
            }else {
                StringBuilder sbUrl = new StringBuilder();

                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.get(jsonRequest) != null) {
                        sbUrl.append('&');
                        sbUrl.append(field.getName());
                        sbUrl.append('=');
                        sbUrl.append(String.valueOf(field.get(jsonRequest)));
                    }
                }
                if (sbUrl.length() > 0) {
                    sbUrl.replace(0, 1, "?");
                    url += sbUrl.toString();
                }

                LogUtils.showInfoLog("get方式url: " + url);
                request  = NoHttp.createStringRequest(url, RequestMethod.GET);
            }

            request.setConnectTimeout(10 * 1000); // 设置连接超时。
            request.setReadTimeout(20 * 1000); // 设置读取超时时间，也就是服务器的响应超时。

            // 添加请求头
            request.addHeader("Content-Type", "application/json");
            String m = "ANDROID";
            request.addHeader("m", m);
            request.addHeader("v", "1.0.0");

        }catch(Exception e){
            LogUtils.showInfoLog("接口访问错误：" + e.getMessage());
        }
        return request;
    }
}
