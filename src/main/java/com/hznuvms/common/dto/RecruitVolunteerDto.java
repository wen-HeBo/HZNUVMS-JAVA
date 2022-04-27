package com.hznuvms.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RecruitVolunteerDto implements Serializable {
    @TableId("v_studentId")
    private String vStudentid;

    private Integer rId;

    @TableField("r_dateSelect")
    private String rDateselect;

    @TableField("r_placeSelect")
    private String rPlaceselect;

    private Integer vSex;

    private String sex;

    private String college;

    private String vName;

    private String vClass;

    private String vCollege;

    private String vPhone;

    private LocalDateTime time;
}
