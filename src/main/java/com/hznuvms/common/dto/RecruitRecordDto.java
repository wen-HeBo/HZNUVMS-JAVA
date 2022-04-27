package com.hznuvms.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RecruitRecordDto implements Serializable {
    private Integer num;
    private String place;
    private String date;
}
