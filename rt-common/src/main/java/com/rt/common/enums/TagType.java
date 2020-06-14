package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum TagType implements IEnum {

    article,
    goods;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
