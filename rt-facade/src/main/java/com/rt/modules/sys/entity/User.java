package com.rt.modules.sys.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
@TableName("tb_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户名
     */
	private String username;
    /**
     * 昵称
     */
	private String nickname;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 是否锁定:0 否，1是
     */
	private Integer locked;
    /**
     * 密码
     */
	private String password;
    /**
     * 盐值
     */
	private String salt;
    /**
     * 性别:0女 1男
     */
	private Integer sex;
    /**
     * 电话
     */
	private String telephone;

    private String avatar;
    /**
     * 更新时间
     */
	@TableField("update_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 创建时间
     */
	@TableField("create_time")
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	/**
	 * 学校地址
	 */
	private String address;
	/**
	 * 用户类型 0:后台用户 1:学校用户
	 */
	private Integer type;
	/**
	 *学校购买最大人数
	 */
	@TableField("max_num")
	private Integer maxNum;
	/**
	 *学校已绑定使用的人数
	 */
	@TableField("use_num")
	private Integer useNum;

	private String  schName;
	/**
	 * 学校所在省
	 */
	private String province;

	/**
	 * 学校所在省ID
	 */
	private Long provinceId;


	private Long sysSchId;


	public Long getSysSchId() {
		return sysSchId;
	}

	public void setSysSchId(Long sysSchId) {
		this.sysSchId = sysSchId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getSchName() {
		return schName;
	}

	public void setSchName(String schName) {
		this.schName = schName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
