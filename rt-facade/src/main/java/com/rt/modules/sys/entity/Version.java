package com.rt.modules.sys.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author edeis
 * @since 2018-02-09
 */
@TableName("tb_version")
public class Version extends Model<Version> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String url;
	private String version;
	@TableField("app_type")
	private Integer appType;
	@TableField("version_code")
	private String versionCode;
	private Integer conpel;
	private String context;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public Integer getConpel() {
		return conpel;
	}

	public void setConpel(Integer conpel) {
		this.conpel = conpel;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Version{" +
			", id=" + id +
			", url=" + url +
			", version=" + version +
			", appType=" + appType +
			", versionCode=" + versionCode +
			", conpel=" + conpel +
			", context=" + context +
			"}";
	}
}
