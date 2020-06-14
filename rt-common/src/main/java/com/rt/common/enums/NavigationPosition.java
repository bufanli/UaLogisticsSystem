package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum NavigationPosition implements IEnum {

    top,

    middle,

    bottom;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
