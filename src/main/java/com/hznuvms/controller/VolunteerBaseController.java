package com.hznuvms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hznuvms.common.dto.BaseDto;
import com.hznuvms.common.lang.Result;
import com.hznuvms.entity.Admin;
import com.hznuvms.entity.VolunteerBase;
import com.hznuvms.mapper.VolunteerBaseMapper;
import com.hznuvms.service.VolunteerBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 温合博
 * @since 2022-04-04
 */
@RestController
@RequestMapping("/volunteer-base")
public class VolunteerBaseController {

    @Autowired
    VolunteerBaseMapper volunteerBaseMapper;

    @Autowired
    VolunteerBaseService volunteerBaseService;

    @PostMapping("/addBase")
    public Result addBase(@RequestBody VolunteerBase volunteerBase){
        return Result.succ("基地新建成功！",volunteerBaseService.save(volunteerBase));
    }

    @GetMapping("/getBaseAll")
    public Result getBaseAll(){
        List<BaseDto> baseDtoList = volunteerBaseMapper.getBase();
        baseDtoList.forEach(item -> {
            if(item.getBStatus().equals(1)){
                item.setStatus("启用中");
            }
            if(item.getBStatus().equals(0)){
                item.setStatus("已停用");
            }
        });
        return Result.succ(baseDtoList);
    }

    @GetMapping("/getBaseById")
    public Result getBaseById(@RequestParam String bid){
        BaseDto baseDto = volunteerBaseMapper.getBaseById(bid);
        if(baseDto.getBStatus().equals(1)){
            baseDto.setStatus("启用中");
        }
        if(baseDto.getBStatus().equals(0)){
            baseDto.setStatus("已停用");
        }
        return Result.succ(baseDto);
    }

    @GetMapping("/updateBase")
    public Result updateBase(@RequestParam String bid,@RequestParam Integer bstatus){
        //只更新一个属性，把id为satudentid的用密码更新为apassword，其他属性不变
        UpdateWrapper<VolunteerBase> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("b_id",bid).set("b_status", bstatus);
        return  Result.succ(volunteerBaseMapper.update(null, updateWrapper));
    }

    @GetMapping("/getBaseList")
    public Result getBaseList(){
        return Result.succ(volunteerBaseMapper.getBaseList());
    }
}
