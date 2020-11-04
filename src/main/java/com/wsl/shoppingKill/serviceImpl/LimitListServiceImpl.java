package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.LimitList;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wsl.shoppingKill.mapper.LimitListMapper;
import com.wsl.shoppingKill.service.LimitListService;
/**
 * @author WangShilei
 */
@Service
public class LimitListServiceImpl extends ServiceImpl<LimitListMapper, LimitList> implements LimitListService{

}
