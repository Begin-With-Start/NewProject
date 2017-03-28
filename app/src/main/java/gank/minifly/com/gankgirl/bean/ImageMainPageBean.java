package gank.minifly.com.gankgirl.bean;

import java.util.List;

/**
 * author ：minifly
 * date: 2017/3/27
 * time: 14:04
 * desc:
 */
public class ImageMainPageBean {

    /**
     * code : 0
     * msg : success
     * data : [{"image_url":"http://img.alicdn.com/imgextra/i2/523216808/TB17w1ZGFXXXXbbXpXXXXXXXXXX_!!523216808-0-tstar.jpg","user_id":"523216808"},{"image_url":"http://img.alicdn.com/imgextra/i1/539549300/TB1S54tIpXXXXa0XFXXXXXXXXXX_!!539549300-0-tstar.jpg","user_id":"539549300"},{"image_url":"http://img.alicdn.com/imgextra/i3/631300490/TB18otCLXXXXXbOaXXXXXXXXXXX_!!0-tstar.jpg","user_id":"631300490"},{"image_url":"http://img.alicdn.com/imgextra/i4/13008032069895178/T1MJE5FaXdXXXXXXXX_!!63153008-0-tstar.jpg","user_id":"63153008"},{"image_url":"http://img.alicdn.com/imgextra/i4/646858747/TB1y49uJVXXXXaQXpXXXXXXXXXX_!!646858747-0-tstar.jpg","user_id":"646858747"},{"image_url":"http://img.alicdn.com/imgextra/i1/687471686/TB1aIeELFXXXXcrapXXXXXXXXXX_!!0-tstar.jpg","user_id":"687471686"},{"image_url":"http://img.alicdn.com/imgextra/i3/717492986/TB1jybTLXXXXXcIaXXXXXXXXXXX_!!717492986-0-tstar.jpg","user_id":"717492986"},{"image_url":"http://img.alicdn.com/imgextra/i1/74386764/TB1tM0cGFXXXXXNXVXXXXXXXXXX_!!74386764-0-tstar.jpg","user_id":"74386764"},{"image_url":"http://img.alicdn.com/imgextra/i4/18272043535448294/T1iMGPFfViXXXXXXXX_!!76438272-0-tstar.jpg","user_id":"76438272"},{"image_url":"http://img.alicdn.com/imgextra/i2/91442126/TB1AWJAHpXXXXclXVXXXXXXXXXX_!!91442126-0-tstar.jpg","user_id":"91442126"},{"image_url":"http://img.alicdn.com/imgextra/i2/96614110/TB1urhoKXXXXXcIXXXXXXXXXXXX_!!0-tstar.jpg","user_id":"96614110"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * image_url : http://img.alicdn.com/imgextra/i2/523216808/TB17w1ZGFXXXXbbXpXXXXXXXXXX_!!523216808-0-tstar.jpg
         * user_id : 523216808
         */

        private String image_url;
        private String user_id;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
