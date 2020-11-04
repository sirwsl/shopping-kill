package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wsl.shoppingKill.mapper.UserMapper;
import com.wsl.shoppingKill.service.UserService;
/**
 * @author WangShilei
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{



}
