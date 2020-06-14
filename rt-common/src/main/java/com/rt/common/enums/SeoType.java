package com.rt.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum SeoType implements IEnum {

    index,

    articleList,

    articleSearch,

    articleContent,

    goodsList,

    goodsSearch,

    goodsContent,

    brandList,

    brandContent;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
