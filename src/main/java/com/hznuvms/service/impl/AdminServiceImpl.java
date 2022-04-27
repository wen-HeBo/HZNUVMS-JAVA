package com.hznuvms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hznuvms.entity.Admin;
import com.hznuvms.mapper.AdminMapper;
import com.hznuvms.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 温合博
 * @since 2022-03-10
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
