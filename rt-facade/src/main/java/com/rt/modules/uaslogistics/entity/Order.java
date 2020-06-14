package com.rt.modules.uaslogistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单
 *
 * @author wm
 * @since 2020-05-13
 */
@TableName("tb_order")
@Data
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 物品重量
     */
    private Integer goodsWeight;

    /**
     * 起始地址
     */
    private String startAddress;

    /**
     * 目的地址
     */
    private String endAddress;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
