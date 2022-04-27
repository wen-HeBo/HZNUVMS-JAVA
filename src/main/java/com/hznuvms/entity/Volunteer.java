package com.hznuvms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 温合博
 * @since 2022-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Volunteer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("v_studentId")
    private String vStudentid;

    private Integer vSex;

    private String vName;

    private String vPhone;

    private String vClass;

    private String vCollege;

    @TableField("v_volunteerHours")
    private Float vVolunteerhours;

    @TableField("v_isBanned")
    private Integer vIsbanned;

    private Integer vPoints;


}
