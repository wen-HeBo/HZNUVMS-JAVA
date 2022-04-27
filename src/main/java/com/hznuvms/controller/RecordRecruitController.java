package com.hznuvms.controller;


import com.hznuvms.common.dto.RecruitRecordDto;
import com.hznuvms.common.dto.RecruitVolunteerDto;
import com.hznuvms.common.lang.Result;
import com.hznuvms.mapper.RecordRecruitMapper;
import com.hznuvms.mapper.RecruitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 温合博
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/record-recruit")
public class RecordRecruitController {

    @Autowired
    RecruitMapper recruitMapper;

    @Autowired
    RecordRecruitMapper recordRecruitMapper;


    @GetMapping("/getRecord")
    public Result getRecord(@RequestParam String rid){
        List data = new ArrayList<RecruitRecordDto>();
        String date = recruitMapper.getData(rid);
        String place = recruitMapper.getPlace(rid);
        String[] dateArr = new String[0];
        String[] placeArr = new String[0];
        if(date != null && date.length() != 0){
            dateArr = date.split(" ");
        }
        if(place != null && place.length() != 0){
            placeArr = place.split(" ");
        }
        if(placeArr.length == 0 && dateArr.length == 0){
            RecruitRecordDto record = new RecruitRecordDto();
            record.setNum(recordRecruitMapper.getNum(rid));
            data.add(record);
        }
        else if(placeArr.length != 0 && dateArr.length == 0){
            for (String item: placeArr){
                RecruitRecordDto record = new RecruitRecordDto();
                record.setNum(recordRecruitMapper.getNumByPlace(rid,item));
                record.setPlace(item);
                data.add(record);
            }
        }
        else if (placeArr.length == 0 && dateArr.length != 0){
            for (String item: dateArr){
                RecruitRecordDto record = new RecruitRecordDto();
                record.setNum(recordRecruitMapper.getNumByDate(rid,item));
                record.setDate(item);
                data.add(record);
            }
        }
        else {
            for (String item1:dateArr){
                for (String item2:placeArr){
                    RecruitRecordDto record = new RecruitRecordDto();
                    record.setNum(recordRecruitMapper.getNumByDatePlace(rid,item1,item2));
                    record.setDate(item1);
                    record.setPlace(item2);
                    data.add(record);
                }
            }
        }
        return Result.succ(data);
    }

    @GetMapping("/getRecruitVolunteer")
    public Result getRecruitVolunteer(@RequestParam String rid){
        List<RecruitVolunteerDto> recruitVolunteers = recordRecruitMapper.getRecruitVolunteer(rid);
        recruitVolunteers.forEach(item -> {
            if(item.getVSex().equals(1)){
                item.setSex("男");
            }
            if(item.getVSex().equals(0)){
                item.setSex("女");
            }
            item.setCollege(item.getVCollege() + item.getVClass());
            item.setTime(LocalDateTime.now());
        });
        return Result.succ(recruitVolunteers);
    }
}

