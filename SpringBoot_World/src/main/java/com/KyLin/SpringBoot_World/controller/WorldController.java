package com.KyLin.SpringBoot_World.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.KyLin.SpringBoot_World.domain.World;
import com.KyLin.SpringBoot_World.mapper.WorldMapper;
import com.KyLin.SpringBoot_World.service.WorldService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/world")
@Tag(name = "World管理", description = "World管理相关接口")
public class WorldController {

    @Autowired
    private WorldMapper worldMapper;

    @Autowired
    private WorldService worldService;

    // 查询全部数据
    @Operation(summary = "查询全部数据")
    @GetMapping("/findAll")
    public List<World> getWorldList() {
        return worldMapper.findAll();
    }

    // 根据ID查询数据
    @Operation(summary = "根据ID查询单个数据")
    @GetMapping("/{id}")
    public World getWorldById(@PathVariable Long id) {
        return worldService.getWorldById(id);
    }

    // 查询数据库中有几条数据
    @Operation(summary = "查询World数量")
    @GetMapping("/getCount")
    public Long countWorlds() {
        return worldService.countWorlds();
    }

    // 添加操作
    @Operation(summary = "添加单个数据")
    @PostMapping("/addOne")
    public String addWorld(@Valid @RequestBody World world, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldError();
            if (error != null) {
                return error.getDefaultMessage();
            }
        }
        worldMapper.insert(world);
        return "添加成功";
    }

    // 修改操作
    // @Operation(summary = "修改单个数据")
    // @PutMapping("/update")
    // public String updateWorld(@RequestBody World world) {
    //     if (worldMapper.update(world) == 1) {
    //         return "更新成功";
    //     } else {
    //         return "World不存在";
    //     }
    // }

    @Operation(summary = "修改单个数据")
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody World world) {
        worldService.update(id, world);
        return ResponseEntity.noContent().build();
    }

    // 删除操作
    @Operation(summary = "删除单个数据")
    @DeleteMapping("/{id}")
    public String deleteWorld(@PathVariable Long id) {
        if (worldMapper.deleteById(id) == 1) {
            return "删除成功";
        } else {
            return "World不存在";
        }
    }

    // 根据ID批量查询数据
    @Operation(summary = "根据ID批量查询数据")
    @GetMapping("/getBatch")
    public List<World> getBatch(@RequestParam List<Long> worldIds) {
        return worldService.getWorldByIds(worldIds);
    }

    // 批量添加
    @Operation(summary = "批量添加数据")
    @PostMapping("/addBatch")
    public Map<String, List<Long>> addBatch(@RequestBody List<World> worldList) {
        Map<String, List<Long>> result = new HashMap<>();
        List<Long> successIds = new ArrayList<>();
        List<Long> failedIds = new ArrayList<>();

        for (World world : worldList) {
            try {
                worldService.addWorld(world);
                successIds.add(world.getId());
            } catch (Exception e) {
                failedIds.add(world.getId());
            }
        }

        result.put("success", successIds);
        result.put("failed", failedIds);
        return result;
    }

    // 批量删除
    @Operation(summary = "批量删除数据")
    @DeleteMapping("/deleteBatch")
    public Map<String, List<Long>> deleteBatch(@RequestBody List<Long> worldIds) {
        Map<String, List<Long>> result = new HashMap<>();
        List<Long> successIds = new ArrayList<>();
        List<Long> failedIds = new ArrayList<>();

        for (Long worldId : worldIds) {
            try {
                worldService.deleteWorld(worldId);
                successIds.add(worldId);
            } catch (Exception e) {
                failedIds.add(worldId);
            }
        }

        result.put("success", successIds);
        result.put("failed", failedIds);
        return result;
    }
    
}
