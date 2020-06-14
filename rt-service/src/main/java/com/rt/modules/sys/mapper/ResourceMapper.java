package com.rt.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rt.modules.sys.entity.Resource;
import com.rt.modules.sys.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author edeis
 * @since 2017-12-06
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    List<Resource> selectResourceByParentId(Long pid);

    List<Resource> selectResourcesByRoleId(Long role_id);

    List<Resource> listAll();

    List<MenuVO> listMenu(@Param("roleId") Long roleId);

    List<MenuVO> listAllMenu();
}
