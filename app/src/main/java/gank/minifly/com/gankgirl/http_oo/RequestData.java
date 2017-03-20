package gank.minifly.com.gankgirl.http_oo;

/**
 * RequestData
 */
public class RequestData implements IRequestData {
	/**
	 * 请求参数是否加在URl上面
	 */
	private boolean isInUrl = false;
	private boolean isReturnOnlyData = false;

	public boolean isReturnOnlyData() {
		return isReturnOnlyData;
	}

	public void setReturnOnlyData(boolean isReturnOnlyData) {
		this.isReturnOnlyData = isReturnOnlyData;
	}

	/**
	 * 连接超时时间
	 */
	private int connectTimeout;

	/**
	 * 读取超时时间
	 */
	private int readTimeout;

	/**
	 * @return 连接超时时间
	 */
	public int getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * @param connectTimeout
	 *            连接超时时间
	 */
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * @return 读取超时时间
	 */
	public int getReadTimeout() {
		return readTimeout;
	}

	/**
	 * @param readTimeout
	 *            读取超时时间
	 */
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public boolean isInUrl() {
		return isInUrl;
	}

	public void setInUrl(boolean isInUrl) {
		this.isInUrl = isInUrl;
	}
}
