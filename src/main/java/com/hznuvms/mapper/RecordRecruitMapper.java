package com.hznuvms.mapper;

import com.hznuvms.common.dto.RecruitVolunteerDto;
import com.hznuvms.entity.RecordRecruit;
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
public interface RecordRecruitMapper extends BaseMapper<RecordRecruit> {
    @Select("select count(*) from record_recruit where r_id = #{rid}")
    Integer getNum(String rid);

    @Select("select count(*) from record_recruit where r_id = #{rid} and r_placeSelect = #{place}")
    Integer getNumByPlace(String rid,String place);

    @Select("select count(*) from record_recruit where r_id = #{rid} and r_dateSelect = #{date}")
    Integer getNumByDate(String rid,String date);

    @Select("select count(*) from record_recruit where r_id = #{rid} and r_dateSelect = #{date} and r_placeSelect = #{place}")
    Integer getNumByDatePlace(String rid,String date,String place);

    @Select("select * from " +
            "(select * from record_recruit natural join volunteer)as volunteer_recruit " +
            "where r_id=#{rid} ")
    List<RecruitVolunteerDto> getRecruitVolunteer(String rid);
}
