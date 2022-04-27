package com.hznuvms.mapper;

import com.hznuvms.common.dto.ActicityVolunteerDto;
import com.hznuvms.entity.RecordVolunteerhours;
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
 * @since 2022-04-04
 */
@Mapper
public interface RecordVolunteerhoursMapper extends BaseMapper<RecordVolunteerhours> {
    @Select("select * from " +
            "(select * from activity natural join record_volunteerhours) as temp" +
            " where v_studentId = #{vstudentid} ")
    List<ActicityVolunteerDto> getActivityVolunteer(String vstudentid);

    @Select("select * from(select * from activity natural join record_volunteerhours) as temp  natural join volunteer")
    List<ActicityVolunteerDto> getVolunteerHours();

    @Select("select * from(select * from activity natural join record_volunteerhours) as temp  natural join volunteer where v_name like #{vname} and a_name like #{aname}")
    List<ActicityVolunteerDto> getHours(String vname,String aname);

    @Select("select a_id,a_name,a_startTime as a_time,count(v_studentId)as num,sum(v_hours) as v_hours " +
            "from(select * from activity natural join record_volunteerhours)as temp " +
            "where a_status = 5 group by a_id")
    List<ActicityVolunteerDto> getHoursAll();

    @Select("select * from(select * from volunteer natural join record_volunteerhours)as temp where a_id = #{aid}")
    List<ActicityVolunteerDto> getHoursByActivity(String aid);

}
