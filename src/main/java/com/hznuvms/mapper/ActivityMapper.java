package com.hznuvms.mapper;

import com.hznuvms.common.dto.ActivityDto;
import com.hznuvms.entity.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 温合博
 * @since 2022-04-01
 */
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
    @Select("select * from Activity")
    List<ActivityDto> getActivity();

    @Select("select * from Activity where a_status = #{status}")
    List<ActivityDto> getActivityQuery(Integer status);

    @Select("select * from Activity where a_status = 2 or a_status = 3")
    List<ActivityDto> getActivitying();

    @Select("select * from Activity where a_status = 4 or a_status = 5")
    List<ActivityDto> getActivityed();
}
