package com.rt.modules.sys.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.rt.modules.sys.entity.Resource;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单VO
 *
 * @Auther: edeis
 * @Date: 2019/1/19 16:52
 * @Description:
 */
public class MenuVO  implements Comparable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date createTime;
    private String description;
    private String icon;
    private Integer isHide;
    private Integer level;
    private String name;
    private String title;
    private Integer sort;
    private String sourceKey;
    private String sourceUrl;
    private Integer type;
    private Date updateTime;
    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    @Override
    public int compareTo(Object o) {
        if (o!=null && o instanceof MenuVO) {
            MenuVO u = (MenuVO) o;
            if(this.getSort()>u.getSort()){
                return 1;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(Object object) {
        if(object==null) return this==null;
        return object.toString().equals(toString());
    }


    @Override
    public String toString() {
        return "MenuVO{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", isHide=" + isHide +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", sourceKey='" + sourceKey + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", type=" + type +
                ", updateTime=" + updateTime +
                ", parentId=" + parentId +
                '}';
    }
}
