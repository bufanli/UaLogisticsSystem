package com.rt.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rt.modules.sys.entity.UserRole;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author edeis
 * @since 2017-12-06
 */
public interface UserRoleService extends IService<UserRole> {

    List<UserRole> listUserRoleByRoleId(Long roleId);

    List<UserRole> listUserRoleByRoleIds(Long[] roleIds);

    List<Long> listUserIdsByRoleId(Long roleId);

}
