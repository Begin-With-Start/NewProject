package gank.minifly.com.gankgirl.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * author ：minifly
 * date: 2017/3/20
 * time: 18:58
 * desc:
 */
public class FuliResponseBean implements Parcelable{

    /**
     * error : false
     * results : [{"_id":"58cf3696421aa90f13178695","createdAt":"2017-03-20T09:55:34.360Z","desc":"3-20","publishedAt":"2017-03-20T11:44:56.224Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-20-17333300_1680707251945881_2009298023053524992_n.jpg","used":true,"who":"daimajia"},{"_id":"58cb5f88421aa95810795c80","createdAt":"2017-03-17T12:01:12.88Z","desc":"3-17","publishedAt":"2017-03-17T12:07:03.767Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-17-17332809_1277469728986540_3201752429582352384_n.jpg","used":true,"who":"dmj"},{"_id":"58c9f47f421aa95810795c73","createdAt":"2017-03-16T10:12:15.342Z","desc":"3-16","publishedAt":"2017-03-16T11:37:02.85Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-16-17333221_108837802984751_2789267558635667456_n.jpg","used":true,"who":"dmj"},{"_id":"58c8adee421aa90f1317866e","createdAt":"2017-03-15T10:58:54.268Z","desc":"3-15","publishedAt":"2017-03-15T11:47:17.825Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-15-17126482_115753765623699_4225645012014071808_n.jpg","used":true,"who":"dmj"},{"_id":"58c72e86421aa90efc4fb6c5","createdAt":"2017-03-14T07:43:02.751Z","desc":"3-14","publishedAt":"2017-03-14T13:17:14.21Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-13-17267506_264626920661300_5781854075880472576_n.jpg","used":true,"who":"带马甲"},{"_id":"58c5e184421aa90f1317864d","createdAt":"2017-03-13T08:02:12.179Z","desc":"3-13","publishedAt":"2017-03-13T12:37:59.782Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-13-17265708_396005157434387_3099040288153272320_n.jpg","used":true,"who":"dmj"},{"_id":"58c1f808421aa95810795c34","createdAt":"2017-03-10T08:49:12.756Z","desc":"3-10","publishedAt":"2017-03-10T11:43:50.30Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-10-17127037_231706780569079_1119464847537340416_n.jpg","used":true,"who":"代码家"},{"_id":"58c0ac1f421aa90f03345143","createdAt":"2017-03-09T09:13:03.655Z","desc":"3-9","publishedAt":"2017-03-09T11:42:30.849Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-09-17127109_1652837611687612_1425055271046086656_n.jpg","used":true,"who":"daimajia"},{"_id":"58bf522a421aa90efc4fb689","createdAt":"2017-03-08T08:36:58.695Z","desc":"3-8","publishedAt":"2017-03-08T11:27:16.65Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-08-17126216_1253875034703554_7520300169779216384_n.jpg","used":true,"who":"daimajia"},{"_id":"58be00ad421aa95810795c13","createdAt":"2017-03-07T08:37:01.730Z","desc":"3-7","publishedAt":"2017-03-07T11:52:11.670Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-07-003645.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Parcelable{
        /**
         * _id : 58cf3696421aa90f13178695
         * createdAt : 2017-03-20T09:55:34.360Z
         * desc : 3-20
         * publishedAt : 2017-03-20T11:44:56.224Z
         * source : chrome
         * type : 福利
         * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-20-17333300_1680707251945881_2009298023053524992_n.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.createdAt);
            dest.writeString(this.desc);
            dest.writeString(this.publishedAt);
            dest.writeString(this.source);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeByte(this.used ? (byte) 1 : (byte) 0);
            dest.writeString(this.who);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this._id = in.readString();
            this.createdAt = in.readString();
            this.desc = in.readString();
            this.publishedAt = in.readString();
            this.source = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readByte() != 0;
            this.who = in.readString();
        }

        public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.results);
    }

    public FuliResponseBean() {
    }

    protected FuliResponseBean(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = in.createTypedArrayList(ResultsBean.CREATOR);
    }

    public static final Creator<FuliResponseBean> CREATOR = new Creator<FuliResponseBean>() {
        @Override
        public FuliResponseBean createFromParcel(Parcel source) {
            return new FuliResponseBean(source);
        }

        @Override
        public FuliResponseBean[] newArray(int size) {
            return new FuliResponseBean[size];
        }
    };
}
