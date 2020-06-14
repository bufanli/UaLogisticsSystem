package com.rt.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rt.modules.sys.entity.User;
import com.rt.modules.sys.dto.query.UserQueryVO;
import com.rt.modules.sys.vo.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author edeis
 * @since 2017-12-06
 */
public interface UserService extends IService<User> {

    void saveOrUpdate(UserVO user);

    IPage<User> page(Integer pageNumber,Integer pageSize,UserQueryVO params);

    User getByUserName(String username);

    void removeById(Long userId);

    User getByUserName(String username, Integer type);
}
