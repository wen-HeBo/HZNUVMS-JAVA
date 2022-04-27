package com.hznuvms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2022-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RecordRecruit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("v_studentId")
    private String vStudentid;

    private Integer rId;

    @TableField("isComplete")
    private Integer isComplete;

    @TableField("isViolation")
    private Integer isViolation;

    @TableField("r_dateSelect")
    private String rDateselect;

    @TableField("r_placeSelect")
    private String rPlaceselect;

    private LocalDateTime time;

}
