package com.KyLin.SpringBoot_User.config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

/**
 * HttpClient使用Get发送请求的基本方法
 */
public class HttpClientTest1 {

    public static void main(String[] args) throws IOException, ParseException {

        // 全局参数配置
        RequestConfig requestConfig = RequestConfig.custom()
                .setResponseTimeout(10, TimeUnit.SECONDS)
                .setConnectTimeout(30, TimeUnit.SECONDS)
                .setConnectionRequestTimeout(10, TimeUnit.SECONDS)
                .build();

        /**
         * 发送一个Get请求
         */
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://www.baidu.com");

        // 给Get请求设置全局参数
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 打印状态码
        // System.out.println(response.getCode());

        // Header[] headers = response.getHeaders();

        // for (Header header : headers) {
        // System.out.println(header.getName());
        // System.out.println(header.getValue());
        // }

        HttpEntity entity = response.getEntity();

        String result = EntityUtils.toString(entity);

        System.out.println(result);

    }

}
