package com.hznuvms.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RecuritDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "r_id", type = IdType.AUTO)
    private Integer rId;

    @NotBlank(message = "活动名称不能为空")
    private String rName;

    @NotBlank(message = "组织名称不能为空")
    private String rOrganizer;

    @NotBlank(message = "招募校区不能为空")
    private String rCampus;

    @NotBlank(message = "活动时间不能为空")
    private String rDate;

    @NotBlank(message = "活动地点不能为空")
    private String rPlace;

    @NotNull(message = "招募人数不能为空")
    @TableField("r_peopleNumber")
    private Integer rPeoplenumber;

    @NotBlank(message = "活动详情不能为空")
    private String rInfo;

    @TableField("r_infoCollect")
    private String rInfocollect;

    @TableField("r_isShowNumber")
    private Integer rIsshownumber;

    @TableField("r_dateSelect")
    private String rDateselect;

    @TableField("r_placeSelect")
    private String rPlaceselect;

    @TableField("r_college")
    private String rCollege;

    @NotBlank(message = "联系人不能为空")
    private String rLeader;

    @TableField("r_isComplete")
    private Integer rIscomplete;

    @TableField("r_isStop")
    private Integer rIsstop;

    private LocalDateTime rDeadline;

    @TableField("r_volunteerHours")
    private Float rVolunteerhours;

    private Integer total;
}
