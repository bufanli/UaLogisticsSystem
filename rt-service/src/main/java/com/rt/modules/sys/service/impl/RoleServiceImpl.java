package com.rt.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rt.modules.sys.entity.Role;
import com.rt.modules.sys.entity.RoleResource;
import com.rt.modules.sys.mapper.RoleMapper;
import com.rt.modules.sys.mapper.RoleResourceMapper;
import com.rt.modules.sys.mapper.UserRoleMapper;
import com.rt.modules.sys.service.RoleService;
import com.rt.modules.sys.dto.query.RoleQueryVO;
import com.rt.modules.sys.vo.RoleVO;
import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  角色服务实现类
 * </p>
 *
 * @author edeis
 * @since 2017-12-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Transactional
    @Override
    public void save(RoleVO roleVO) {
        roleVO.setCreateTime(DateTime.now().toDate());
        roleVO.setUpdateTime(DateTime.now().toDate());
        Role role = roleVO.convertTo();
        this.baseMapper.insert(role);
        this.grant(role.getId(),roleVO.getResourceIds());
    }

    @Transactional
    @Override
    public void update(RoleVO roleVO) {
        roleVO.setUpdateTime(DateTime.now().toDate());
        Role role = roleVO.convertTo();
        this.baseMapper.updateById(roleVO.convertTo());
        this.grant(role.getId(),roleVO.getResourceIds());
    }

    @Override
    public IPage<Role> page(Integer pageNumber, Integer pageSize, RoleQueryVO params) {
        IPage<Role> pageData = this.page(new Page<Role>(pageNumber, pageSize),null);
        return pageData;
    }

    @Override
    public List<Role> listAll() {
        return this.list(null);
    }

    //    @Cacheable(cacheNames = "role",key = "'role'+#userId")
    @Override
    public List<Role> listByUserId(Long userId) {
        return userRoleMapper.selectRoleByUserId(userId);
    }

    @Transactional
    @Override
    public void removeById(Long roleId) {
        roleResourceMapper.delete(new QueryWrapper<RoleResource>().eq("role_id",roleId));
        super.removeById(roleId);
    }

    @Override
    public Role getByRoleKey(String roleKey) {
        QueryWrapper<Role> qw = new QueryWrapper();
        qw.eq("role_key",roleKey);
        return this.baseMapper.selectOne(qw);
    }

    private  void grant(Long roleId,List<Long> resourceIds) {
        roleResourceMapper.delete(new QueryWrapper<RoleResource>().eq("role_id",roleId));
        if(CollectionUtils.isNotEmpty(resourceIds)){
            for(Long resourceId:resourceIds){
                RoleResource resource = new RoleResource(roleId,resourceId);
                roleResourceMapper.insert(resource);
            }
        }
    }
}
