package com.ydl.service.impl;

import com.ydl.dao.MemberDao;
import com.ydl.pojo.Member;
import com.ydl.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: SayHello
 * @Date: 2023/3/4 11:00
 * @Introduction:
 */
@Service(interfaceClass = MemberService.class)
@Slf4j
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        memberDao.add(member);
    }
}
