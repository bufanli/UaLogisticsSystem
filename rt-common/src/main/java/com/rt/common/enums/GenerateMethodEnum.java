package com.rt.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @Auther: edeis
 * @Date: 2019/1/29 11:33
 * @Description:
 */
public enum GenerateMethodEnum implements IEnum<Integer> {
    NONE(0,"从不"),//从不
    EAGER(1,"立即"),//立刻
    LAZY(2,"延迟");//延迟

    private Integer value;
    private String desc;

    GenerateMethodEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        return getDesc();
    }
}
