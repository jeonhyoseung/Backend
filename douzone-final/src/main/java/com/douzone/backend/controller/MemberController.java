package com.douzone.backend.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.backend.service.MemberService;
import com.douzone.backend.vo.MemberVO;

@RestController
public class MemberController {

	@Autowired
	MemberService memberService;

	// 아이디 중복확인 : id 받아와서 status : true / false 넘겨주기
	@PostMapping("/checkDupId.do")
	public Map<String, Object> checkDupId(@RequestBody MemberVO memberVO) {
		Map<String, Object> result = new HashMap<>();
		int idCount = memberService.checkDupId(memberVO.getUserid());

		if (idCount > 0) {
			result.put("status", false);
		} else {
			result.put("status", true);
		}
		System.out.println("아이디 중복확인 함 => " + result);
		return result;
	}

	// 이메일 중복확인 : email 받아와서 status : true / false 넘겨주기
	@PostMapping("/checkDupEmail.do")
	public Map<String, Object> checkDupEmail(@RequestBody MemberVO memberVO) {
		Map<String, Object> result = new HashMap<>();
		int emailCount = memberService.checkDupEmail(memberVO.getUseremail());

		if (emailCount > 0) {
			result.put("status", false);
		} else {
			result.put("status", true);
		}
		System.out.println("이메일 중복확인 함 => " + result);
		return result;
	}

	// 회원가입 : memberVO 받아와서 status : true / false 넘겨주기
	@PostMapping("/register.do")
	public Map<String, Object> register(@RequestBody MemberVO memberVO) {
		Map<String, Object> result = new HashMap<>();

		System.out.println(memberVO);
		try {
			memberService.memberInsert(memberVO);
			result.put("status", true);
		} catch (Exception e) {
			result.put("status", false);
		}

		System.out.println("회원가입 함 => " + result);
		return result;
	}

	// 로그인 : memberVO 받아와서 status : true / false, name 넘겨주기
	@PostMapping("/login.do")
	public Map<String, Object> login(@RequestBody MemberVO memberVO, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();

		HttpSession session = request.getSession();

		try {
			MemberVO member = memberService.memberSelectForLogin(memberVO);

			if (member != null) {
				session.setAttribute("isLogOn", true);
				session.setAttribute("memberId", member.getUserid());

				result.put("isLogOn", true);
				result.put("memberInfo", member);
				result.put("status", true);
				result.put("name", member.getUsername());
			} else {
				result.put("status", false);
			}
		} catch (Exception e) {
			result.put("status", false);
		}

		System.out.println("로그인 함 => " + session.getAttribute("memberInfo"));
		return result;
	}

	// 아이디 찾기 : memberVO (name, email) 받아와서 status : true / false, userid, name 넘겨주기
	@PostMapping("/findId.do")
	public Map<String, Object> findId(@RequestBody MemberVO memberVO) {
		Map<String, Object> result = new HashMap<>();

		try {
			MemberVO member = memberService.memberSelectbyEmail(memberVO);

			if (member != null) {
				result.put("status", true);
				result.put("userid", member.getUserid());
				result.put("name", member.getUsername());
			} else {
				result.put("status", false);
			}
		} catch (Exception e) {
			result.put("status", false);
		}

		System.out.println("아이디 찾기함 => " + result);
		return result;
	}

	// 로그아웃
	@PostMapping("/logout.do")
	public Map<String, Object> logout(@RequestBody MemberVO memberVO, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();

		HttpSession session = request.getSession();

		session.setAttribute("isLogOn", false);
		session.removeAttribute("memberInfo");

		return result;
	}

	// 마이페이지
	@PostMapping("/mypage.do")
	public Map<String, Object> mypage(@RequestBody MemberVO memberVO, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();

		HttpSession session = request.getSession();

		if (!session.getAttribute("memberId").equals(memberVO.getUserid())) {
			result.put("status", false);
			result.put("message", "로그인 해주세요.");
			return result;
		}

		try {
			MemberVO member = memberService.memberSelectbyId(memberVO);
			result.put("status", true);
			result.put("memberVO", member);

		} catch (Exception e) {
			result.put("status", false);
		}

		System.out.println("회원정보 조회함 => " + result);
		return result;
	}
	
	// 비밀번호 확인 
	@PostMapping("/checkPw.do")
	public Map<String, Object> checkPw(@RequestBody MemberVO memberVO, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();

		HttpSession session = request.getSession();

		if (!session.getAttribute("memberId").equals(memberVO.getUserid())) {
			result.put("status", false);
			result.put("message", "로그인 해주세요.");
			return result;
		}

		try {
			MemberVO member = memberService.memberSelectForLogin(memberVO);
			
			if (member != null) {
				result.put("status", true);
			} else {
				result.put("status", false);
			}

		} catch (Exception e) {
			result.put("status", false);
		}

		System.out.println("비밀번호 확인함 => " + result);
		return result;
	}
}
