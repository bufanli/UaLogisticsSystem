package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

public enum OrderType implements IEnum {


    general,

    exchange;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
