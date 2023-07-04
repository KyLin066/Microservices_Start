package com.KyLin.SpringBoot_World.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KyLin.SpringBoot_World.domain.World;
import com.KyLin.SpringBoot_World.mapper.WorldMapper;

@Service
public class WorldService {
    
    @Autowired
    private WorldMapper worldMapper;

    // 根据ID查询单个数据
    public World getWorldById(Long id) {
        return worldMapper.selectById(id);
    }

    //根据ID批量查询数据
    public List<World> getWorldByIds(List<Long> worldIds) {
        return worldMapper.selectByIds(worldIds);
    }

    // 查询数据数量
    public Long countWorlds() {
        return worldMapper.count();
    }

    // 添加数据
    public void addWorld(World world) {
        worldMapper.insert(world);
    }

    // 修改操作
    public void update(Long id, World world) {
        world.setId(id);
        worldMapper.update(world);
    }

    // 删除数据
    public void deleteWorld(Long worldId) {
        worldMapper.deleteById(worldId);
    }

    // public void addBatch(List<World> worldList) {
    // for (World world : worldList){
    // worldMapper.insert(world);
    // }
    // }

}
