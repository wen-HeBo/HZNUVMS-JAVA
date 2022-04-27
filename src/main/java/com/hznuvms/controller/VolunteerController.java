package com.hznuvms.controller;


import com.hznuvms.common.lang.Result;
import com.hznuvms.mapper.VolunteerMapper;
import com.hznuvms.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 温合博
 * @since 2022-03-30
 */
@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    VolunteerMapper volunteerMapper;

    @Autowired
    VolunteerService volunteerService;

    @GetMapping("/getVolunteer")
    public Result getVoluterr(@RequestParam String vstudentid){
        return Result.succ(volunteerService.getById(vstudentid));
    }
}
