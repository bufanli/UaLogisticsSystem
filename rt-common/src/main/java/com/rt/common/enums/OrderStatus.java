package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

public enum OrderStatus implements IEnum {


    pendingPayment,

    pendingReview,

    pendingShipment,

    shipped,

    received,

    completed,

    failed,

    canceled,

    denied;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
