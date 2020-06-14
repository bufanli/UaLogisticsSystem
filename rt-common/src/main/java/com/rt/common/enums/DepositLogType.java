package com.rt.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum DepositLogType implements IEnum {
    recharge,

    adjustment,

    payment,

    refunds;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
