package com.rt.modules.sys.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkinglotVO {

    /**
     * 停车场id
     */
    private Long id;

    /**
     * 停车场名称
     */
    private String name;

    /**
     * 该停车场车位数量
     */
    private Integer parkingCount;

    /**
     * 车场状态 0未启用 1启用
     */
    private Integer status;

    /**
     * 生成状态 0 未生成 1 已生成
     */
    private Integer generateStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
