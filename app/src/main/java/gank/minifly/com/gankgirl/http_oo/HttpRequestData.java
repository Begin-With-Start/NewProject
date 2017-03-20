package gank.minifly.com.gankgirl.http_oo;

/**
 * HTTP访问用RequestData
 */
public class HttpRequestData implements IRequestData {

    /**
     * 请求URL
     */
    private String requestUrl = "";
    
    /**
     * @param requestUrl
     * 请求URL
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * @return 请求URL
     */
    public String getRequestUrl() {
        return requestUrl;
    }

}
