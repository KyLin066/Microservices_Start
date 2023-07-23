package com.KyLin.SpringBoot_User.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;

import com.KyLin.SpringBoot_User.domain.User;
import com.KyLin.SpringBoot_User.domain.WorldDTO;
import com.KyLin.SpringBoot_User.mapper.UserMapper;
import com.KyLin.SpringBoot_User.utils.HttpUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private org.springframework.cloud.client.discovery.DiscoveryClient discoveryClient;

    ObjectMapper objectMapper = new ObjectMapper();
    TypeReference<List<WorldDTO>> typeReference = new TypeReference<List<WorldDTO>>() {};

    // 修改操作
    public void update(Long id, User user) {
        user.setId(id);
        userMapper.update(user);
    }

    // HttpClient查询World的全部数据
    public List<WorldDTO> getWorld() throws IOException, ParseException {
        List<WorldDTO> worlds = new ArrayList<>();
        List<org.springframework.cloud.client.ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        ServiceInstance instance = instances.get(0);
        String url = instance.getUri().toString() + "/world/findAll";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            int statusCode = response.getCode();
            String reasonPhrase = response.getReasonPhrase();
            if (statusCode == HttpStatus.SC_OK) {
                // 获取成功
                HttpEntity entity = response.getEntity();
                String json = EntityUtils.toString(entity);
                worlds = objectMapper.readValue(json, typeReference);
            } else {
                // 处理错误响应
                System.err.println("Error getting worlds: " + reasonPhrase);
            }
        } finally {
            response.close();
        }
        return worlds;
    }

    // HttpClient添加单个World数据
    public String addWorld() throws IOException, ParseException, JSONException {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        ServiceInstance instance = instances.get(0);
        String url = instance.getUri().toString() + "/world/addOne";

        CloseableHttpClient httpConnection = HttpUtils.getHttpConnection();
        HttpPost httpPost = new HttpPost(url);

        JSONObject param = new JSONObject();
        param.put("active", "1");
        param.put("worldName", "Planet666");
        param.put("worldAge", 66);
        param.put("worldDesc", "666");
        param.put("worldRadius", 66.6);
        param.put("worldWeight", 6.6);

        StringEntity stringEntity = new StringEntity(param.toString());
        httpPost.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse execute = httpConnection.execute(httpPost);

        String result = EntityUtils.toString(execute.getEntity());
        return "添加单个World数据结果：" + result;

    }

    // HttpClient修改单个World数据
    public void updateWorld(WorldDTO world) throws IOException {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        ServiceInstance instance = instances.get(0);
        String url = instance.getUri().toString() + "/world/update/6";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        String json = "{\"active\":" + world.getActive() + ", \"worldName\":\"" + world.getWorldName() + "\", \"worldAge\":" + world.getWorldAge() + ", \"worldDesc\":\"" + world.getWorldDesc() + "\", \"worldRadius\":" + world.getWorldRadius() + ", \"worldWeight\":" + world.getWorldWeight() + "}";
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPut.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPut);
        try {
            int statusCode = response.getCode();
            String reasonPhrase = response.getReasonPhrase();
            if (statusCode == HttpStatus.SC_NO_CONTENT) {
                // 更新成功
                System.out.println("User updated successfully");
            } else {
                // 处理错误响应
                System.err.print("Error updating user:" + reasonPhrase);
            }
        } finally {
            response.close();
        }
    }

    // HttpClient删除单个World数据
    public void deleteWorld() throws IOException {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        ServiceInstance instance = instances.get(0);
        String url = instance.getUri().toString() + "/world/34";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        CloseableHttpResponse response = httpClient.execute(httpDelete);
        try {
            int statusCode = response.getCode();
            String reasonPhrase = response.getReasonPhrase();
            if (statusCode == HttpStatus.SC_NO_CONTENT) {
                // 删除成功
                System.out.println("World deleted successfully");
            } else {
                // 处理错误响应
                System.err.println("Error deleting world: " + reasonPhrase);
            }
        } finally {
            response.close();
        }
    }

}
