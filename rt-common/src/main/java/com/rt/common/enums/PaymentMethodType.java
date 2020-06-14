package com.rt.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;


public enum PaymentMethodType implements IEnum {

    deliveryAgainstPayment,
    cashOnDelivery;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
