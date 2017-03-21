package gank.minifly.com.gankgirl.bean;

import gank.minifly.com.gankgirl.common.http.http_oo.HttpRequestData;

/**
 * author ：minifly
 * date: 2017/3/20
 * time: 15:25
 * desc:
 */
public class RequestExpressBean extends HttpRequestData {
    //key=8489e1b3f15b4b0c94646948195858c8&cityname=杭州&paybyvas=false
    private String key="8489e1b3f15b4b0c94646948195858c8",cityname="杭州";
    private boolean paybyvas = false;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public boolean isPaybyvas() {
        return paybyvas;
    }

    public void setPaybyvas(boolean paybyvas) {
        this.paybyvas = paybyvas;
    }
}
