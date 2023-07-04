package com.KyLin.SpringBoot_User.config;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.hc.client5.http.async.methods.SimpleBody;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequests;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.http.ParseException;

/**
 * HttpClient异步请求
 */
public class HttpClientTest3 {

    public static void main(String[] args) throws IOException, ParseException, InterruptedException, ExecutionException {

        CloseableHttpAsyncClient createDefault = HttpAsyncClients.createDefault();

        SimpleHttpRequest request = SimpleHttpRequests.get("http://www.baidu.com");

        Future<SimpleHttpResponse> future = createDefault.execute(request,null);

        SimpleHttpResponse simpleHttpResponse = future.get();

        simpleHttpResponse.getBodyText();

        SimpleBody body = simpleHttpResponse.getBody();

        System.out.println(body.getBodyText());

    }

}
