package com.hznuvms.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "b_id", type = IdType.AUTO)
    private Integer bId;

    private String bName;

    private String bOrganization;

    private Integer bStatus;

    private String status;

    private String bInfo;

    private Integer num;

    private Integer total;

    private Integer hours;
}
