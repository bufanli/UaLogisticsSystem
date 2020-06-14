package com.rt.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rt.modules.sys.entity.Resource;
import com.rt.modules.sys.mapper.ResourceMapper;
import com.rt.modules.sys.service.ResourceService;
import com.rt.modules.sys.vo.MenuVO;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Transactional
    @Override
    public boolean save(Resource entity) {
        entity.setCreateTime(DateTime.now().toDate());
        entity.setUpdateTime(DateTime.now().toDate());
        return super.save(entity);
    }

    @Transactional
    @Override
    public boolean updateById(Resource entity) {
        entity.setUpdateTime(DateTime.now().toDate());
        return super.updateById(entity);
    }

    @Override
    public List<Resource> listByRoleId(Long role_id) {
        return this.baseMapper.selectResourcesByRoleId(role_id);
    }

    @Override
    public List<Resource> listParentId(Long pid) {
        return this.baseMapper.selectResourceByParentId(pid);
    }

    @Override
    public List<Resource> listAll() {
        return this.baseMapper.listAll();
    }

    @Override
    public List<MenuVO> listAllMenu() {
        return this.baseMapper.listAllMenu();
    }

    @Override
    public List<MenuVO> listMenu(Long roleId) {
        return this.baseMapper.listMenu(roleId);
    }

}
