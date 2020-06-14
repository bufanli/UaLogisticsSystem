package com.rt.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;


public enum PaymentMethodMethod implements IEnum {

    online,
    offline;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
