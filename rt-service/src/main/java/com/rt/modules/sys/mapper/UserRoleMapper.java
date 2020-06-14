package com.rt.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rt.modules.sys.entity.Role;
import com.rt.modules.sys.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author edeis
 * @since 2017-12-06
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<Role> selectRoleByUserId(Long userId);
}
