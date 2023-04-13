package com.ydl.service;

import com.ydl.pojo.Member;

/**
 * @Author: SayHello
 * @Date: 2023/3/4 10:57
 * @Introduction:
 */
public interface MemberService {

    Member findByTelephone(String telephone);

    void add(Member member);
}
