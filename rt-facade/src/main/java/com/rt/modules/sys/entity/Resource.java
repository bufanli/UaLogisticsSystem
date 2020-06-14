package com.rt.modules.sys.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author edeis
 * @since 2017-12-06
 */
@TableName("tb_resource")
public class Resource extends Model<Resource>  {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	@TableField("create_time")
	private Date createTime;
	private String description;
	private String icon;
	@TableField("is_hide")
	private Integer isHide;
	private Integer level;
	private String name;
	private Integer sort;
	@TableField("source_key")
	private String sourceKey;
	@TableField("source_url")
	private String sourceUrl;
	private Integer type;
	@TableField("update_time")
	private Date updateTime;
	@TableField("parent_id")
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
	protected Serializable pkVal() {
		return this.id;
	}

    @Override
	public String toString() {
		return "Resource{" +
			", id=" + id +
			", createTime=" + createTime +
			", description=" + description +
			", icon=" + icon +
			", isHide=" + isHide +
			", level=" + level +
			", name=" + name +
			", sort=" + sort +
			", sourceKey=" + sourceKey +
			", sourceUrl=" + sourceUrl +
			", type=" + type +
			", updateTime=" + updateTime +
			", parentId=" + parentId +
			"}";
	}

}
