package com.hznuvms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

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
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "a_id", type = IdType.AUTO)
    private Integer aId;

    @NotBlank(message = "活动名称不能为空")
    private String aName;

    @NotBlank(message = "活动地点不能为空")
    private String aPlace;

    @NotBlank(message = "组织名称不能为空")
    private String aOrganizer;

    private String aCode;

    @TableField("a_startTime")
    private LocalDate aStarttime;

    @TableField("a_endTime")
    private LocalDate aEndtime;

    @NotBlank(message = "活动详情不能为空")
    private String aInfo;

    private Integer aStatus;

    private String approval;

}
