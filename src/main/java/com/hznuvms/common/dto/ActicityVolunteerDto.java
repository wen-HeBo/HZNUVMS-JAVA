package com.hznuvms.common.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ActicityVolunteerDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer aId;

    @TableId("v_studentId")
    private String vStudentid;

    private String vName;

    private String college;

    private String vClass;

    private String vCollege;

    private Float vHours;

    private LocalDate vTime;

    private LocalDate aTime;

    private String aName;

    private String aOrganizer;

    private String aCode;

    private Integer num;
}
