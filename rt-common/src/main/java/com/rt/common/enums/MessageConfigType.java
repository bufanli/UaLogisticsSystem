package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum MessageConfigType implements IEnum {


    registerMember,

    createOrder,

    updateOrder,

    cancelOrder,

    reviewOrder,

    paymentOrder,

    refundsOrder,

    shippingOrder,

    returnsOrder,

    receiveOrder,

    completeOrder,

    failOrder;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
