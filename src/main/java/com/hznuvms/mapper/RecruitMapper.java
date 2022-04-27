package com.hznuvms.mapper;

import com.hznuvms.common.dto.RecuritDto;
import com.hznuvms.entity.Admin;
import com.hznuvms.entity.Recruit;
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
 * @since 2022-03-20
 */
@Mapper
public interface RecruitMapper extends BaseMapper<Recruit> {
    @Select("select * ,coalesce(num,0)as total from recruit NATURAL left join" +
            "(select r_id,COUNT(r_id)as num from record_recruit GROUP BY r_id)AS volunteer_recruit" +
            " WHERE r_isComplete = 0")
    List<RecuritDto> getRecruiting();

    @Select("select * from recruit where r_isComplete = 1")
    List<Recruit> getRecruited();

    @Select("select r_dateSelect from recruit where r_id = #{rid}")
    String getData(String rid);

    @Select("select r_placeSelect from recruit where r_id = #{rid}")
    String getPlace(String rid);
}
