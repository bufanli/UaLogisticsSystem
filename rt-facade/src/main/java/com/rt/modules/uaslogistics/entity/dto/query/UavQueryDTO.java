package com.rt.modules.uaslogistics.entity.dto.query;

import lombok.Data;

/**
 * 无人机查询DTO
 */
@Data
public class UavQueryDTO {

    /**
     * 无人机名称
     */
    private String uavName;

    /**
     * 无人机编号
     */
    private String uavCode;

    /**
     * 无人机型号
     */
    private String uavType;

    /**
     * 状态 0 空闲 1 占用
     */
    private Integer status;
}
