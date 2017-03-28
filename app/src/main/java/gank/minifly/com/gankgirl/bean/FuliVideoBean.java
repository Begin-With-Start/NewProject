package gank.minifly.com.gankgirl.bean;

import java.util.List;

/**
 * Created by minifly on 2017-03-28.
 */

public class FuliVideoBean extends BaseBean{
    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }
    public class ResultsBean{
        private String description;
        private String url;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
