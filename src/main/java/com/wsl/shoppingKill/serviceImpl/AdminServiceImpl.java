package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.entity.Admin;
import com.wsl.shoppingKill.mapper.AdminMapper;
import com.wsl.shoppingKill.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @author wsl
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements AdminService {



}
