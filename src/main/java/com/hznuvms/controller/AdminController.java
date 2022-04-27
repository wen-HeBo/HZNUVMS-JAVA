package com.hznuvms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hznuvms.common.lang.Result;
import com.hznuvms.entity.Admin;
import com.hznuvms.mapper.AdminMapper;
import com.hznuvms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 温合博
 * @since 2022-03-10
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;

    //@RequiresAuthentication
    @PostMapping("/add")
    public Result addUser(@Validated @RequestBody Admin admin) {
        if (adminService.getOne(new QueryWrapper<Admin>().eq("a_StudentId", admin.getAStudentid())) != null) {
            return Result.fail(admin.getAStudentid() + "用户已存在");
        }
        return Result.succ("添加用户成功！", adminService.save(admin));
        // Admin admin = adminService.getById("2018212212300");
        // return Result.succ(admin);
    }

    @GetMapping("/query")
    public PageInfo<Admin> query(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> admins = adminMapper.getAdmin();

        Page page = (Page) admins;
        PageInfo<Admin> result = new PageInfo<>(admins);
        return result;
    }

    @GetMapping("getUserById")
    public Result getUserById(@RequestParam String astudentid) {
        Admin admin = adminService.getById(astudentid);
        if(admin == null){
            return Result.fail("用户不存在");
        }
        return Result.succ(admin);
    }

    @PostMapping("updateUser")
    public Result updateUser(@RequestBody Admin admin) {
        return Result.succ("修改成功 ",adminService.updateById(admin));
    }

    @GetMapping("deleteUser")
    public Result deleteUser(@RequestParam String astudentid) {
        return Result.succ("删除成功",adminMapper.deleteById(astudentid));
    }

    @GetMapping("updatePassword")
    public Result updatePassword(@RequestParam String astudentid,@RequestParam String apassword){
        Admin admin = adminService.getById(astudentid);
        if(admin == null){
            return Result.fail("用户不存在");
        }
        else{
            //只更新一个属性，把id为satudentid的用密码更新为apassword，其他属性不变
            UpdateWrapper<Admin> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("a_studentId",astudentid).set("a_password", apassword);
            return  Result.succ("密码修改成功",adminMapper.update(null, updateWrapper));
        }
    }
}
