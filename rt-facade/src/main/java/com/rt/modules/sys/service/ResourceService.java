package com.rt.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rt.modules.sys.entity.Resource;
import com.rt.modules.sys.vo.MenuVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author edeis
 * @since 2017-12-06
 */
public interface ResourceService extends IService<Resource> {



    List<Resource> listByRoleId(Long role_id);

    List<Resource> listParentId(Long pid);

    List<Resource> listAll();

    List<MenuVO> listAllMenu();

    List<MenuVO> listMenu(Long roleId);


}
