package priv.ga0weI.demoshrio;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

public class HttpClientHelper {
    public static String sendPost(String urlParam, String rememberthing ) throws HttpException, IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(urlParam);
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        postMethod.addRequestHeader("Content-Type", "application/json");
        if (rememberthing!=" ")
            postMethod.addRequestHeader("Cookie",rememberthing);
        postMethod.setRequestBody("name=gaowei&password=gaowei");
        httpClient.executeMethod(postMethod);

        String resultforhead = postMethod.getResponseHeader("Set-Cookie").toString();
        System.out.println("响应头中的Set-Cookie："+resultforhead);
        String result = postMethod.getResponseBodyAsString();
        postMethod.releaseConnection();
        return result;
    }

    public static String sendGet(String urlParam) throws HttpException, IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建GET请求方法实例对象
        GetMethod getMethod = new GetMethod(urlParam);
        // 设置post请求超时时间
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        getMethod.addRequestHeader("Content-Type", "application/json");

        httpClient.executeMethod(getMethod);
        String resultforhead = getMethod.getResponseHeaders().toString();
        System.out.println("响应头："+resultforhead);
        String result = getMethod.getResponseBodyAsString();
        getMethod.releaseConnection();
        return result;
    }

    public static void main(String[] args) throws HttpException, IOException {
        String url = "http://www.baidu.com";
        System.out.println(sendPost(url," "));
        System.out.println(sendGet(url));
    }
}
