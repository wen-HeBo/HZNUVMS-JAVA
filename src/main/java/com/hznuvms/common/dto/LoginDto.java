package com.hznuvms.common.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "id不能为空")
    @TableId("a_studentId")
    private String aStudentid;

    @NotBlank(message = "密码不能为空")
    private String aPassword;
}