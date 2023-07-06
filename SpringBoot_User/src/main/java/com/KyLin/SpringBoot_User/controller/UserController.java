package com.KyLin.SpringBoot_User.controller;

import java.io.IOException;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException; 
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KyLin.SpringBoot_User.domain.User;
import com.KyLin.SpringBoot_User.domain.WorldDTO;
import com.KyLin.SpringBoot_User.mapper.UserMapper;
import com.KyLin.SpringBoot_User.service.UserService;
import com.KyLin.SpringBoot_User.utils.HttpUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "用户管理", description = "用户管理相关接口")
public class UserController {
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    // 查询全部用户
    @Operation(summary = "查询全部用户")
    @GetMapping("/search")
    public List<User> getUserList() {
        return userMapper.findAll();
    }

    // 添加操作
    @Operation(summary = "添加单个用户")
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userMapper.insert(user);
        return "添加成功";
    }

    // 修改操作
    // @Operation(summary = "修改单个用户")
    // @PutMapping("/update")
    // public String updateUser(@RequestBody User user) {
    //     if (userMapper.update(user) == 1) {
    //         return "更新成功";
    //     } else {
    //         return "用户不存在";
    //     }
    // }

    @Operation(summary = "修改单个用户")
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody User user) {
        userService.update(id, user);
        return ResponseEntity.noContent().build();
    }

    // 删除操作
    @Operation(summary = "删除单个用户")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (userMapper.deleteById(id) == 1) {
            return "删除成功";
        } else {
            return "用户不存在";
        }
    }

    /*
     * 使用HttpClient进行添加操作
     */
    @Operation(summary = "HttpClient测试接口")
    @GetMapping("/test")
    public void test() throws IOException, JSONException, ParseException {
        CloseableHttpClient httpConnection = HttpUtils.getHttpConnection();
        HttpPost httpPost = new HttpPost("http://localhost:8080/user/add");

        JSONObject param = new JSONObject();
        param.put("name", "KyLin");
        param.put("age", 23);

        StringEntity stringEntity = new StringEntity(param.toString());
        httpPost.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse execute = httpConnection.execute(httpPost);

        String result = EntityUtils.toString(execute.getEntity());
        System.out.println("调用post 返回结果：" + result);
    }

    // 查询World全部数据
    @Operation(summary = "HttpClient查询World全部数据")
    @GetMapping("/findAllWorld")
    public List<WorldDTO> getWorld() throws ParseException, IOException {
        return userService.getWorld();
    }

    // 添加单个World数据
    @Operation(summary = "HttpClient添加单个World数据")
    @GetMapping("/addWorld")
    public String addWorld() throws ParseException, IOException, JSONException {
        return userService.addWorld();
    }

    // HttpClient修改单个World数据
    @Operation(summary = "HttpClient修改单个World数据")
    @PutMapping("/updateWorld")
    public ResponseEntity<Void> updateWorld(@RequestBody WorldDTO world) throws IOException {
        userService.updateWorld(world);
        return ResponseEntity.noContent().build();
    }

    // 删除单个World数据
    @Operation(summary = "HttpClient删除单个World数据")
    @DeleteMapping("/deleteWorld")
    public ResponseEntity<Void> deleteWorld() throws IOException {
        userService.deleteWorld();
        return ResponseEntity.noContent().build();
    }

}
