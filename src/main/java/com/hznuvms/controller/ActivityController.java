package com.hznuvms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hznuvms.common.dto.ActivityDto;
import com.hznuvms.common.lang.Result;
import com.hznuvms.entity.Activity;
import com.hznuvms.mapper.ActivityMapper;
import com.hznuvms.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 温合博
 * @since 2022-04-01
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    ActivityService activityService;

    @PostMapping("/addActivity")
    public Result addActivity(@RequestBody Activity activity){
        return Result.succ(activityService.save(activity));
    }

    @GetMapping("/getActivity")
    public PageInfo<ActivityDto> getActivity(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ActivityDto> activities = activityMapper.getActivity();

        activities.forEach(item->{
            if(item.getAStatus().equals(0)){
                item.setStatus("审核中");
            }
            if(item.getAStatus().equals(1)){
                item.setStatus("审核未通过");
            }
            if(item.getAStatus().equals(2)){
                item.setStatus("审核通过");
            }
            if(item.getAStatus().equals(3)){
                item.setStatus("活动中");
            }
            if(item.getAStatus().equals(4)){
                item.setStatus("志愿时数表待提交");
            }
            if(item.getAStatus().equals(5)){
                item.setStatus("志愿时数表已提交");
            }
        });

        PageInfo<ActivityDto> result = new PageInfo<>(activities);
        return result;
    }

    @GetMapping("/getActivityQuery")
    public PageInfo<ActivityDto> getActivityQuery(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize, @RequestParam String select) {
        PageHelper.startPage(pageNum, pageSize);
        List<ActivityDto> activities = new ArrayList<>();
        if(select.equals("2")){
            activities = activityMapper.getActivityQuery(0);
        }
        if(select.equals("3")){
            activities = activityMapper.getActivityQuery(1);
        }
        if(select.equals("4")){
            activities = activityMapper.getActivitying();
        }
        if(select.equals("5")){
            activities = activityMapper.getActivityed();
        }


        activities.forEach(item->{
            if(item.getAStatus().equals(4)){
                item.setStatus("志愿时数表待提交");
            }
            if(item.getAStatus().equals(5)){
                item.setStatus("志愿时数表已提交");
            }
        });

        PageInfo<ActivityDto> result = new PageInfo<>(activities);
        return result;
    }

    @GetMapping("/getActivityById")
    public Result getActivityById(@RequestParam String aid){
        return Result.succ(activityService.getById(aid));
    }

    @GetMapping("/getActivityApproval")
    public Result getActivityApproval(){
        List<ActivityDto> activitiy = activityMapper.getActivityQuery(0);
        return Result.succ(activitiy);
    }

    @GetMapping("/approvalActivity")
    public Result approvalActivity(@RequestParam String aid,@RequestParam Integer status,@RequestParam String approval){
        Activity activity = activityService.getById(aid);
        if(activity == null){
            return Result.fail("活动不存在");
        }
        else {
            UpdateWrapper<Activity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("a_id",aid).set("approval",approval).set("a_status",status);
            return Result.succ("审批成功",activityMapper.update(null,updateWrapper));
        }
    }

    @GetMapping("updateActivity")
    public Result updateActivity(@RequestParam String aid){
        UpdateWrapper<Activity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("a_id",aid).set("a_status",5);
        return Result.succ(activityMapper.update(null,updateWrapper));
    }
}
