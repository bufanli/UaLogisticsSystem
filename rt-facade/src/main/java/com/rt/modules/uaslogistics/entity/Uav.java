package com.rt.modules.uaslogistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 无人机
 *
 * @since 2020-05-13
 */
@TableName("tb_uav")
@Data
public class Uav extends Model<Uav>{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    /**
     * 无人机电量
     */
    private Integer uavPower;

    /**
     * 状态 0 空闲 1 占用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否上下架 0 代表下架 1代表上架
     */
    private Integer isValid;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
