package com.rt.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rt.modules.sys.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author edeis
 * @since 2017-12-06
 */
public interface UserMapper extends BaseMapper<User> {

    User findByUserName(String username);
}
