package com.rt.modules.uaslogistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rt.modules.uaslogistics.entity.Order;
import com.rt.modules.uaslogistics.mapper.OrderMapper;
import com.rt.modules.uaslogistics.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wm
 * @since 2020-05-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
