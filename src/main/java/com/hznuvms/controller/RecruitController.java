package com.hznuvms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hznuvms.common.dto.RecuritDto;
import com.hznuvms.common.lang.Result;
import com.hznuvms.entity.Recruit;
import com.hznuvms.mapper.RecruitMapper;
import com.hznuvms.service.RecruitService;
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
 * @since 2022-03-20
 */
@RestController
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    RecruitMapper recruitMapper;

    @Autowired
    RecruitService recruitService;

    @PostMapping("/addRecruit")
    public Result addRecruit(@RequestBody @Validated Recruit recruit){
        return Result.succ("招募发布成功！",recruitService.save(recruit));
    }

    @GetMapping("/getRecruiting")
    public List<RecuritDto> getRecruiting() {
        return recruitMapper.getRecruiting();
    }

    @GetMapping("/getRecruited")
    public PageInfo<Recruit> getRecruited(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Recruit> recruits = recruitMapper.getRecruited();

        Page page = (Page) recruits;
//        System.out.println("每页展示条数：" + page.getPageSize());
//        System.out.println("总条数：" + page.getTotal());
//        System.out.println("当前页：" + page.getPageNum());
//        System.out.println("总页数：" + page.getPages());
        PageInfo<Recruit> result = new PageInfo<>(recruits);
        return result;
    }

    @GetMapping("getRecruitById")
    public Result getRecruitById(@RequestParam String rid) {
        Recruit recruit = recruitService.getById(rid);
        if(recruit == null){
            return Result.fail("招募不存在");
        }
        return Result.succ(recruit);
    }

    @GetMapping("changeRecruitStatus")
    public Result changeRecruitStatus(@RequestParam String rid,@RequestParam Integer riscomplete){
        Recruit recruit = recruitService.getById(rid);
        if(recruit == null){
            return Result.fail("该招募不存在");
        }
        else{
            UpdateWrapper<Recruit> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("r_id",rid).set("r_isComplete", riscomplete);
            return  Result.succ("密码修改成功",recruitMapper.update(null, updateWrapper));
        }
    }

    @PostMapping("updateRecruit")
    public Result updateRecruit(@RequestBody Recruit recruit) {
        return Result.succ("修改成功 ",recruitService.updateById(recruit));
    }

    @GetMapping("/deleteRecruit")
    public Result updateRecruit(@RequestParam String rid){
        return Result.succ("删除成功",recruitMapper.deleteById(rid));
    }
}
