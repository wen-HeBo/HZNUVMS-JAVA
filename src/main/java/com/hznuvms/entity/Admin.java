package com.hznuvms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 温合博
 * @since 2022-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("a_studentId")
    @NotBlank(message = "id不能为空")
    private String aStudentid;

    @TableId("a_Password")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 16, message = "密码长度必须是6-16个字符")
    private String aPassword;

    @NotBlank(message = "姓名不能为空")
    private String aName;

    // @NotBlank(message = "性别不能为空")
    private Integer aSex;

    // @NotBlank(message = "手机号不能为空")
    // @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String aPhone;

    // @NotBlank(message = "班级不能为空")
    private String aClass;

    // @NotBlank(message = "学院不能为空")
    private String aCollege;

    @NotBlank(message = "组织不能为空")
    private String aOrganization;

    @NotBlank(message = "权限不能为空")
    private String aPower;

}
