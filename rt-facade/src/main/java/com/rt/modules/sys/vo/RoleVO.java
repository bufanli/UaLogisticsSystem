package com.rt.modules.sys.vo;

import com.google.common.base.Converter;
import com.rt.modules.sys.entity.Role;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @Auther: edeis
 * @Date: 2019/1/23 11:45
 * @Description:
 */
public class RoleVO {

    private Long id;
    private Date createTime;
    private String description;
    private String name;
    private String roleKey;
    private Integer status;
    private Date updateTime;
    private List<Long> resourceIds;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Long> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Long> resourceIds) {
        this.resourceIds = resourceIds;
    }


    public Role convertTo(){
        RoleConvert convert = new RoleConvert();
        Role o = convert.convert(this);
        return o;
    }

    public RoleVO convertFor(Role o) {
        RoleConvert convert = new RoleConvert();
        RoleVO vo = convert.reverse().convert(o);
        return vo;
    }


    private static class RoleConvert extends Converter<RoleVO, Role> {

        @Override
        protected Role doForward(RoleVO vo) {
            Role o = new Role();
            BeanUtils.copyProperties(vo,o);
            return o;
        }

        @Override
        protected RoleVO doBackward(Role o) {
            RoleVO vo = new RoleVO();
            BeanUtils.copyProperties(o,vo);
            return vo;
        }
    }
}
