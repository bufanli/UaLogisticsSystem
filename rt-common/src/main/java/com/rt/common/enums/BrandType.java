package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum BrandType implements IEnum {

    text,
    image;

    @Override
    public Serializable getValue() {
        return this.ordinal();
    }
}
