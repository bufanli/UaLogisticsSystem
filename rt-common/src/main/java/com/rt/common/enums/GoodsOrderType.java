package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 商品排序类型
 */
public enum GoodsOrderType implements IEnum {

    topDesc,

    priceAsc,

    priceDesc,

    salesDesc,

    scoreDesc,

    dateDesc;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
