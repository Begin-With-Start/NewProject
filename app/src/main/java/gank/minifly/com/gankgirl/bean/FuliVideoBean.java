package gank.minifly.com.gankgirl.bean;

import java.util.List;

/**
 * Created by minifly on 2017-03-28.
 */

public class FuliVideoBean {
    private String code;

    private String msg;

    private List<Data> data ;

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
    public class Data {
        private String description;

        private String url;

        public void setDescription(String description){
            this.description = description;
        }
        public String getDescription(){
            return this.description;
        }
        public void setUrl(String url){
            this.url = url;
        }
        public String getUrl(){
            return this.url;
        }

    }
}
