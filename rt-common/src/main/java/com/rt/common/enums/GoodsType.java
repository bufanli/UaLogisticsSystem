package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 商品类型
 */
public enum GoodsType implements IEnum {

    general,//普通商品

    exchange,//兑换商品

    gift;//礼品商品

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
