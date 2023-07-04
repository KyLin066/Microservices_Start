package com.KyLin.SpringBoot_User.config;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import com.KyLin.SpringBoot_User.utils.HttpUtils;

/**
 * HttpClient使用连接池发送请求的方法
 */
public class HttpClientTest2 {

    public static void main(String[] args) throws IOException, ParseException {

        CloseableHttpClient httpConnection = HttpUtils.getHttpConnection();

        HttpGet httpGet = new HttpGet("http://www.baidu.com");

        CloseableHttpResponse execute = httpConnection.execute(httpGet);

        String s = EntityUtils.toString(execute.getEntity());

        System.out.println(s);

    }

}
