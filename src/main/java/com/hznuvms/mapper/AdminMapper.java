package com.hznuvms.mapper;

import com.hznuvms.entity.Admin;
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
 * @since 2022-03-10
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    // 查所有询用户信息
    @Select("select * from Admin where a_studentId <> '1001'")
    List<Admin> getAdmin();
}
