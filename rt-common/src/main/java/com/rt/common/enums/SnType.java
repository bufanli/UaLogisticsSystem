package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum SnType implements IEnum {

    goods,

    order,

    paymentLog,

    payment,

    refunds,

    shipping,

    returns;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
