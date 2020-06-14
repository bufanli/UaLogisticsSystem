package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum StockLogType implements IEnum {

    stockIn,

    stockOut;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
