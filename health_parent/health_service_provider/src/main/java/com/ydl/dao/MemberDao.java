package com.ydl.dao;

import com.ydl.pojo.Member;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: SayHello
 * @Date: 2023/3/3 17:16
 * @Introduction:
 */
public interface MemberDao {

    Member findByTelephone(@Param("telePhone") String telePhone);

    void add(Member member);

    Integer findMemberCountByDate(@Param("date") String date);

    Integer findMemberTotalCount();

}
