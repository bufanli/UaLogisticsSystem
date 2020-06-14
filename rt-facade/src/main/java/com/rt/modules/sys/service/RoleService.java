package com.rt.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rt.modules.sys.entity.Role;
import com.rt.modules.sys.dto.query.RoleQueryVO;
import com.rt.modules.sys.vo.RoleVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author edeis
 * @since 2017-12-06
 */
public interface RoleService extends IService<Role> {

    void save(RoleVO roleVO);

    void update(RoleVO roleVO);

    IPage<Role> page(Integer pageNumber,Integer pageSize,RoleQueryVO params);

    List<Role> listAll();

    List<Role> listByUserId(Long userId);

    void removeById(Long roleId);


    Role getByRoleKey(String roleKey);
}
