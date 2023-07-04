package com.KyLin.SpringBoot_World.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KyLin.SpringBoot_World.domain.World;

public interface WorldMapper {
    
    // 查询全部数据
    @Select("SELECT * FROM t_world")
    List<World> findAll();

    // 根据ID查询单条数据
    @Select("SELECT * FROM t_world WHERE id = #{id}")
    World selectById(Long id);

    // 根据ID批量查询数据
    @Select({
        "<script>",
        "SELECT * FROM t_world WHERE id IN ",
        "<foreach collection='list' item='id' open='(' separator=',' close=')'>",
        "#{id}",
        "</foreach>",
        "</script>"
    })
    List<World> selectByIds(List<Long> worldIds);

    // 查询数据库中有几条数据
    @Select("SELECT count(*) FROM t_world")
    Long count();

    // 新增数据
    @Insert("INSERT INTO t_world(uuid, active, worldName, worldAge, worldDesc, worldRadius, worldWeight, createTime) VALUES(uuid(), #{active}, #{worldName}, #{worldAge}, #{worldDesc}, #{worldRadius}, #{worldWeight}, now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(World world);

    // 修改数据
    @Update("UPDATE t_world SET active=#{active}, worldName=#{worldName}, worldAge=#{worldAge}, worldDesc=#{worldDesc}, worldRadius=#{worldRadius}, worldWeight=#{worldWeight} WHERE id=#{id}")
    int update(World world);

    // 根据ID删除数据
    @Delete("DELETE FROM t_world WHERE id=#{id}")
    int deleteById(Long id);
    
    // 批量添加
    // @Insert("INSERT INTO t_world(uuid, active, worldName, worldAge, worldDesc, worldRadius, worldWeight, createTime) VALUES(uuid(), #{active}, #{worldName}, #{worldAge}, #{worldDesc}, #{worldRadius}, #{worldWeight}, now())")
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    // int batchInsert(List<TWorld> tWorldList);

}
