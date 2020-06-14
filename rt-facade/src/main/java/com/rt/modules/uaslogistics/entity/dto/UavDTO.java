package com.rt.modules.uaslogistics.entity.dto;

import lombok.Data;

/**
 * 无人机传输对象
 */
@Data
public class UavDTO {

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
     * 无人机负载重量
     */
    private Integer uavWeight;
}
