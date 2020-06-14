package com.rt.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rt.common.vo.HashPassword;
import com.rt.modules.sys.entity.User;
import com.rt.modules.sys.entity.UserRole;
import com.rt.modules.sys.mapper.UserMapper;
import com.rt.modules.sys.mapper.UserRoleMapper;
import com.rt.modules.sys.service.UserService;
import com.rt.modules.sys.vo.UserVO;
import com.rt.modules.sys.dto.query.UserQueryVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author edeis
 * @since 2017-12-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleMapper userRoleMapper;


//    @Override
//    public IPage<User> page(IPage<User> page, Wrapper<User> wrapper) {
//        IPage<User> pageData = super.page(page, wrapper);
//        if(pageData != null){
//            List<User> users = pageData.getRecords();
//            if(CollectionUtils.isNotEmpty(users)){
//                for (User user : users) {
//                    List<Role> roles = userRoleMapper.selectRoleByUserId(user.getId());
//                    user.setRoles(roles);
//                }
//            }
//        }
//        return pageData;
//    }

    @Override
    public IPage<User> page(Integer pageNumber, Integer pageSize, UserQueryVO params) {
        QueryWrapper<User> ew = new QueryWrapper<User>();
        String username = params.getUsername();
        if(StringUtils.isNotBlank(username)){
            ew.like("username", username);
        }
        IPage<User> pageData = super.page(new Page<User>(pageNumber, pageSize),ew);
        return pageData;
    }

    @Override
    public User getByUserName(String username) {
        return this.baseMapper.findByUserName(username);
    }

    private void grant(User user, List<Long> roleIds) {
        Assert.notNull(user, "用户不存在");
        Assert.state(!"admin".equals(user.getUsername()), "超级管理员用户不能修改管理角色");
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id",user.getId()));
        if(CollectionUtils.isNotEmpty(roleIds)){
            for(Long roleId:roleIds){
                userRoleMapper.insert(new UserRole(user.getId(),Long.valueOf(roleId)));
            }
        }
    }

    @Transactional
    @Override
    public void saveOrUpdate(UserVO userVo) {
        //设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
        User user = userVo.convertTo();
        if (StringUtils.isNotBlank(user.getPassword())) {
            HashPassword hashPassword = HashPassword.encrypt(user.getPassword());
            user.setSalt(hashPassword.salt);
            user.setPassword(hashPassword.password);
        }
        if (user.getId() != null) {
            user.setUpdateTime(DateTime.now().toDate());
            this.baseMapper.updateById(user);
        } else {
            user.setCreateTime(DateTime.now().toDate());
            user.setUpdateTime(DateTime.now().toDate());
            this.baseMapper.insert(user);
        }
        this.grant(user,userVo.getRoleIds());
    }


    @Transactional
    @Override
    public void removeById(Long userId) {
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id",userId));
        super.removeById(userId);
    }

    @Override
    public User getByUserName(String username, Integer type) {
        QueryWrapper<User> qw = new QueryWrapper();
        qw.eq("username",username);
        qw.eq("type",type);
        return this.baseMapper.selectOne(qw);
    }


}
