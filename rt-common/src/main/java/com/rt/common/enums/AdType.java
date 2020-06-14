package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum AdType implements IEnum {

    text,
    image;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
