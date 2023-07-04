package com.KyLin.SpringBoot_User.utils;

import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;

public class HttpUtils {

    // 创建连接池管理对象
    static PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();

    // 全局参数配置
    static RequestConfig requestConfig = RequestConfig.custom()
            .setResponseTimeout(10, TimeUnit.SECONDS)
            .setConnectTimeout(30, TimeUnit.SECONDS)
            .setConnectionRequestTimeout(10, TimeUnit.SECONDS)
            .build();

    static {
        // 配置最大的连接数
        manager.setMaxTotal(300);
        // 每个路由最大连接数，路由是根据host来管理的，所以这里的数量不太容易把握；
        manager.setDefaultMaxPerRoute(20);
    }

    // 获取连接对象，从连接池里面去获取
    public static CloseableHttpClient getHttpConnection() {
        CloseableHttpClient build = HttpClients.custom()
                .setConnectionManager(manager)
                .setDefaultRequestConfig(requestConfig)
                .build();
        return build;
    }

}
