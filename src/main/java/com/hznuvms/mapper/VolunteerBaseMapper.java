package com.hznuvms.mapper;

import com.hznuvms.common.dto.BaseDto;
import com.hznuvms.entity.VolunteerBase;
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
public interface VolunteerBaseMapper extends BaseMapper<VolunteerBase> {
    @Select("select * from volunteer_base")
    List<BaseDto> getBase();

    @Select("select * from volunteer_base where b_id = #{bid}")
    BaseDto getBaseById(String bid);

    @Select("select b_id,b_name,b_organization,count(a_id)as total,sum(hours)as hours, any_value(num)as num from" +
            "(select a_id,a_code,count(a_code)as num,sum(v_hours)as hours from" +
            " activity natural join record_volunteerhours group by a_id)" +
            "as temp1 ,volunteer_base " +
            "where temp1.a_code = volunteer_base.b_id group by b_id")
    List<BaseDto> getBaseList();
}
