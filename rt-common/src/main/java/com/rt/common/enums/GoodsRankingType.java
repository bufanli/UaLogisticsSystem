package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 商品统计维度类型
 */
public enum GoodsRankingType implements IEnum {

    score,

    scoreCount,

    weekHits,

    monthHits,

    hits,

    weekSales,

    monthSales,

    sales;//礼品商品

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
