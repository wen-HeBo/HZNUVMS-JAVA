package com.hznuvms.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ActivityDto implements Serializable {
    @TableId(value = "a_id", type = IdType.AUTO)
    private Integer aId;

    private String aName;

    private String aPlace;

    private String aOrganizer;

    private LocalDate aStarttime;

    private LocalDate aEndtime;

    private Integer aStatus;

    private String status;
}
