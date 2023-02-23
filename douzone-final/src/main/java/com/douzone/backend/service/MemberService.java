package com.douzone.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.backend.dao.MemberDAO;
import com.douzone.backend.vo.MemberVO;


@Service("memberService")
public class MemberService {
	
	@Autowired
	MemberDAO memberDAO;

	public int checkDupId(String userid) {
		return memberDAO.checkDupId(userid);
	}

	public void memberInsert(MemberVO memberVO) {
		memberDAO.memberInsert(memberVO);
	}

	public MemberVO memberSelectForLogin(MemberVO memberVO) {
		MemberVO tempMember = memberDAO.memberSelectbyId(memberVO.getUserid());
		
		if (tempMember.getUserpwd().equals(memberVO.getUserpwd())) {
			return tempMember;
		} else {
			return null;
		}
	}

	public int checkDupEmail(String useremail) {
		return memberDAO.checkDupEmail(useremail);
	}

	public MemberVO memberSelectbyEmail(MemberVO memberVO) {
		MemberVO tempMember = memberDAO.memberSelectbyEmail(memberVO.getUseremail());
		
		if (tempMember.getUsername().equals(memberVO.getUsername())) {
			return tempMember;
		} else {
			return null;
		}
	}

	public MemberVO memberSelectbyId(MemberVO memberVO) {
		MemberVO member = memberDAO.memberSelectbyId(memberVO.getUserid());
		
		String ph1 = member.getUserphone().substring(0, 3);
		String ph2 = member.getUserphone().substring(3, 7);
		String ph3 = member.getUserphone().substring(7, 11);

		StringBuilder phone = new StringBuilder();
		phone.append(ph1).append("-").append(ph2).append("-").append(ph3);
		member.setUserphone(phone.toString());
		
		StringBuilder pwd = new StringBuilder();
		int len = (member.getUserpwd()).length();
		for (int i=0; i<len; i++) {
			pwd.append("*");
		}
		member.setUserpwd(pwd.toString());
		
		return member;
	}
}
