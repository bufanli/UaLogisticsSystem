package com.rt.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rt.modules.sys.entity.UserRole;
import com.rt.modules.sys.mapper.UserRoleMapper;
import com.rt.modules.sys.service.UserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="edeis@163.com">edeis</a>
 * @version V1.0.0
 * @date 2018-3-28
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<UserRole> listUserRoleByRoleId(Long roleId) {
        QueryWrapper<UserRole> ew = new QueryWrapper<UserRole>();
        ew.eq("role_id",roleId);
        return this.list(ew);
    }

    @Override
    public List<UserRole> listUserRoleByRoleIds(Long[] roleIds) {
        QueryWrapper<UserRole> ew = new QueryWrapper<UserRole>();
        ew.in("role_id",roleIds);
        return this.list(ew);
    }

    @Override
    public List<Long> listUserIdsByRoleId(Long roleId) {
        List<UserRole> userRoles = this.listUserRoleByRoleId(roleId);
        List<Long> ids = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(userRoles)){
            for (UserRole userRole : userRoles) {
                ids.add(userRole.getUserId());
            }
        }
       return ids;
    }
}
