package com.hznuvms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hznuvms.common.dto.ActicityVolunteerDto;
import com.hznuvms.common.lang.Result;
import com.hznuvms.entity.Activity;
import com.hznuvms.entity.RecordVolunteerhours;
import com.hznuvms.mapper.RecordVolunteerhoursMapper;
import com.hznuvms.service.RecordVolunteerhoursService;
import com.hznuvms.util.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 温合博
 * @since 2022-04-04
 */
@RestController
@RequestMapping("/record-volunteerhours")
public class RecordVolunteerhoursController {
    @Autowired
    RecordVolunteerhoursMapper recordVolunteerhoursMapper;

    @Autowired
    RecordVolunteerhoursService recordVolunteerhoursService;

    @GetMapping("/getVolunteerHours")
    public Result getVolunteerHours(@RequestParam String vstudentid){
        return Result.succ(recordVolunteerhoursMapper.getActivityVolunteer(vstudentid));
    }

    @GetMapping("/getVolunteerHoursList")
    public PageInfo<ActicityVolunteerDto> getVolunteerHoursList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<ActicityVolunteerDto> acticityVolunteerDtoList = recordVolunteerhoursMapper.getVolunteerHours();
        acticityVolunteerDtoList.forEach(item->{
            item.setCollege(item.getVCollege()+item.getVClass());
        });
        PageInfo<ActicityVolunteerDto> result = new PageInfo<>(acticityVolunteerDtoList);
        return result;
    }

    @GetMapping("getHours")
    public Result getHours(@RequestParam String vname,@RequestParam String aname){
        List<ActicityVolunteerDto> activityVolunteerDtoList = recordVolunteerhoursMapper.getHours('%'+vname+'%','%'+aname+'%');
        activityVolunteerDtoList.forEach(item->{
            item.setCollege(item.getVCollege()+item.getVClass());
        });
        return Result.succ(activityVolunteerDtoList);
    }

    @GetMapping("/updateVolunteerHours")
    public Result updateVolunteerHours(@RequestParam String vstudentid,@RequestParam Integer aid,@RequestParam Integer vhours){
        UpdateWrapper<RecordVolunteerhours> updateWrapper = new UpdateWrapper<>();
        //设置条件
        Map<String,Object> params = new HashMap<>();
        params.put("v_studentId",vstudentid);
        params.put("a_id",aid);
        updateWrapper.allEq(params).set("v_hours", vhours);
        return Result.succ("志愿时数修改成功！",recordVolunteerhoursMapper.update(null,updateWrapper));
    }

    @GetMapping("/deleteHours")
    public Result deleteHours(@RequestParam String vstudentid,@RequestParam Integer aid){
        UpdateWrapper<RecordVolunteerhours> updateWrapper = new UpdateWrapper<>();
        //设置条件
        Map<String,Object> params = new HashMap<>();
        params.put("v_studentId",vstudentid);
        params.put("a_id",aid);
        updateWrapper.allEq(params);
        return Result.succ("删除成功！",recordVolunteerhoursMapper.delete(updateWrapper));
    }

    @GetMapping("/getHoursAll")
    public PageInfo<ActicityVolunteerDto> getHourAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<ActicityVolunteerDto> acticityVolunteerDtoList = recordVolunteerhoursMapper.getHoursAll();
        PageInfo<ActicityVolunteerDto> result = new PageInfo<>(acticityVolunteerDtoList);
        return result;
    }

    @GetMapping("/getHoursByActivity")
    public Result getHoursByAActivity(@RequestParam String aid){
        List<ActicityVolunteerDto> acticityVolunteerDtoList = recordVolunteerhoursMapper.getHoursByActivity(aid);
        acticityVolunteerDtoList.forEach(item->{
            item.setCollege(item.getVCollege()+item.getVClass());
        });
        return Result.succ(acticityVolunteerDtoList);
    }

    @PostMapping("/import")
    public Result importData(MultipartFile file) throws IOException {
        List<RecordVolunteerhours> recordVolunteerhoursList = PoiUtils.parseFileList(file);
        if(recordVolunteerhoursService.saveBatch(recordVolunteerhoursList)) {
            return Result.succ("批量导入成功！");
        }
        else {
            return Result.fail("批量导入失败！");
        }
    }
}
