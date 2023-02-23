package com.douzone.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.douzone.backend.vo.MemberVO;

@Mapper
@Repository("memberDAO")
public interface MemberDAO {

	int checkDupId(String userid);

	void memberInsert(MemberVO memberVO);

	MemberVO memberSelectbyId(String userid);

	int checkDupEmail(String useremail);

	MemberVO memberSelectbyEmail(String useremail);

}
