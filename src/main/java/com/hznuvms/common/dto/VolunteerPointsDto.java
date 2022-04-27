package com.hznuvms.common.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class VolunteerPointsDto implements Serializable {
    @TableId("v_studentId")
    private String vStudentid;

    private String vName;

    private String college;

    private String vClass;

    private String vCollege;

    private String vPoints;
}
