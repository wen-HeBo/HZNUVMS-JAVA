package com.hznuvms.mapper;

import com.hznuvms.entity.Volunteer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 温合博
 * @since 2022-04-04
 */
@Mapper
public interface VolunteerMapper extends BaseMapper<Volunteer> {
}
