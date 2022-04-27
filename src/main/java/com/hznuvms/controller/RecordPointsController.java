package com.hznuvms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hznuvms.common.dto.ActicityVolunteerDto;
import com.hznuvms.common.dto.VolunteerPointsDto;
import com.hznuvms.common.lang.Result;
import com.hznuvms.entity.Admin;
import com.hznuvms.entity.RecordPoints;
import com.hznuvms.entity.Volunteer;
import com.hznuvms.mapper.RecordPointsMapper;
import com.hznuvms.mapper.VolunteerMapper;
import com.hznuvms.service.RecordPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 温合博
 * @since 2022-04-05
 */
@RestController
@RequestMapping("/record-points")
public class RecordPointsController {

    @Autowired
    VolunteerMapper volunteerMapper;

    @Autowired
    RecordPointsMapper recordPointsMapper;

    @Autowired
    RecordPointsService recordPointsService;

    @GetMapping("getBlackList")
    public PageInfo<VolunteerPointsDto> getBlackList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<VolunteerPointsDto> volunteerPointsDtoList = recordPointsMapper.getBliakList();
        volunteerPointsDtoList.forEach(item->{
            item.setCollege(item.getVCollege()+item.getVClass());
        });
        PageInfo<VolunteerPointsDto> result = new PageInfo<>(volunteerPointsDtoList);
        return result;
    }

    @GetMapping("getPointsList")
    public PageInfo<VolunteerPointsDto> getPointsList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<VolunteerPointsDto> volunteerPointsDtoList = recordPointsMapper.getPointsList();
        volunteerPointsDtoList.forEach(item->{
            item.setCollege(item.getVCollege()+item.getVClass());
        });
        PageInfo<VolunteerPointsDto> result = new PageInfo<>(volunteerPointsDtoList);
        return result;
    }

    @GetMapping("getPoints")
    public Result getPoints(@RequestParam String vstudentid){
        return Result.succ(recordPointsMapper.getPointsChange(vstudentid));
    }

    @PostMapping("addPoints")
    public Result addPoints(@RequestBody RecordPoints recordPoints){
        if(recordPointsService.save(recordPoints)){
            UpdateWrapper<Volunteer> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("v_studentId",recordPoints.getVStudentid()).set("v_points", recordPoints.getPChange());
            return  Result.succ("修改信用积分成功！",volunteerMapper.update(null, updateWrapper));
        }else{
            return Result.fail("修改信用积分失败！");
        }
    }

}
