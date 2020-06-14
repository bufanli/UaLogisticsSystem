package com.rt.modules.uaslogistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rt.modules.uaslogistics.entity.Uav;
import com.rt.modules.uaslogistics.entity.dto.UavDTO;
import com.rt.modules.uaslogistics.entity.dto.query.UavQueryDTO;
import com.rt.modules.uaslogistics.mapper.UavMapper;
import com.rt.modules.uaslogistics.service.IUavService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wm
 * @since 2020-05-13
 */
@Service
public class UavServiceImpl extends ServiceImpl<UavMapper, Uav> implements IUavService {

    @Override
    public IPage<Uav> page(Integer pageNumber, Integer pageSize, UavQueryDTO params) {
        QueryWrapper<Uav> uavQueryWrapper = new QueryWrapper<>();
        String uavName = params.getUavName();
        if (StringUtils.isNotBlank(uavName)) {
            uavQueryWrapper.like("uav_name", uavName);
        }
        String uavCode = params.getUavCode();
        if (StringUtils.isNotBlank(uavCode)) {
            uavQueryWrapper.like("uav_code", uavCode);
        }
        String uavType = params.getUavType();
        if (StringUtils.isNotBlank(uavType)) {
            uavQueryWrapper.like("uav_type", uavType);
        }
        Integer status = params.getStatus();
        if (null != status) {
            uavQueryWrapper.eq("status", status);
        }
        IPage<Uav> pageData = super.page(new Page<>(pageNumber, pageSize), uavQueryWrapper);
        return pageData;
    }

    @Override
    public boolean saveUav(UavDTO uavDTO) {
        Uav uav = new Uav();
        BeanUtils.copyProperties(uavDTO, uav);
        uav.setCreateTime(LocalDateTime.now());
        uav.setUpdateTime(LocalDateTime.now());
        // 新增时无人机是空闲的
        uav.setStatus(0);
        // 新增时无人机电量是满的
        uav.setUavPower(100);
        return super.save(uav);
    }
}












