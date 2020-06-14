package com.rt.modules.uaslogistics.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rt.modules.uaslogistics.entity.Uav;
import com.rt.modules.uaslogistics.entity.dto.UavDTO;
import com.rt.modules.uaslogistics.entity.dto.query.UavQueryDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wm
 * @since 2020-05-13
 */
public interface IUavService extends IService<Uav> {

    /**
     * 自定义无人机分页查询
     * @param pageNumber
     * @param pageSize
     * @param params
     * @return
     */
    IPage<Uav> page(Integer pageNumber, Integer pageSize, UavQueryDTO params);

    /**
     * 新增无人机信息
     * @param uavDTO
     * @return
     */
    boolean saveUav(UavDTO uavDTO);
}
