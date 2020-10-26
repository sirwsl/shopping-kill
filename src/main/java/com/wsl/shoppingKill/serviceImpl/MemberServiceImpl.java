package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.entity.Member;
import com.wsl.shoppingKill.mapper.MemberMapper;
import com.wsl.shoppingKill.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper,Member> implements MemberService {



}
