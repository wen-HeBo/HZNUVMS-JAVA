package com.hznuvms.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hznuvms.common.dto.LoginDto;
import com.hznuvms.common.lang.Result;
import com.hznuvms.entity.Admin;
import com.hznuvms.service.AdminService;
import com.hznuvms.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    AdminService adminService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){

       Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("a_StudentId",loginDto.getAStudentid()));
        Assert.notNull(admin,"用户不存在");

        if(!admin.getAPassword().equals(loginDto.getAPassword())){
            return Result.fail("密码不正确");
        }

        String jwt = jwtUtils.generateToken(admin.getAStudentid());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        return Result.succ(MapUtil.builder()
                .put("id",admin.getAStudentid())
                .put("name",admin.getAName())
                .put("power",admin.getAPower())
                .map()
        );
    }

    @RequiresAuthentication
    @GetMapping("/loginout")
    public Result loginout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
