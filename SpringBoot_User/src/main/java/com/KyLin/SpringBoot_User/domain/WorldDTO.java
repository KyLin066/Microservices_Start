package com.KyLin.SpringBoot_User.domain;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class WorldDTO {
    
    @Schema(description = "自增ID")
    private Long id;

    @Schema(description = "唯一标识")
    private String uuid;

    @Schema(description = "可用性标记")
    private Short active;

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    private String worldName;

    @Schema(description = "年龄")
    private Integer worldAge;

    @Schema(description = "描述")
    private String worldDesc;

    @Schema(description = "半径")
    private Float worldRadius;

    @Schema(description = "重量")
    private Double worldWeight;

    @Schema(description = "创建时间")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public Integer getWorldAge() {
        return worldAge;
    }

    public void setWorldAge(Integer worldAge) {
        this.worldAge = worldAge;
    }

    public String getWorldDesc() {
        return worldDesc;
    }

    public void setWorldDesc(String worldDesc) {
        this.worldDesc = worldDesc;
    }

    public Float getWorldRadius() {
        return worldRadius;
    }

    public void setWorldRadius(Float worldRadius) {
        this.worldRadius = worldRadius;
    }

    public Double getWorldWeight() {
        return worldWeight;
    }

    public void setWorldWeight(Double worldWeight) {
        this.worldWeight = worldWeight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    // @PrePersist
    // public void init() {
    //     this.uuid = UUID.randomUUID().toString().replace("-", "");
    // }

}
