package com.hznuvms.mapper;

import com.hznuvms.common.dto.VolunteerPointsDto;
import com.hznuvms.entity.RecordPoints;
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
 * @since 2022-04-05
 */
@Mapper
public interface RecordPointsMapper extends BaseMapper<RecordPoints> {
    @Select("select * from volunteer where v_points < 60")
    List<VolunteerPointsDto> getBliakList();

    @Select("select * from volunteer")
    List<VolunteerPointsDto> getPointsList();

    @Select("select * from record_points where v_studentId = #{vstudentid}")
    List<RecordPoints> getPointsChange(String vstudentid);

}
