package com.hznuvms.entity;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RecordVolunteerhours implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("v_studentId")
    private String vStudentid;

    @TableId("a_id")
    private Integer aId;

    private Float vHours;

    private LocalDate vTime;


}
