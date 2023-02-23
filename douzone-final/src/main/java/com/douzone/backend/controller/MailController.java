package com.douzone.backend.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.backend.service.MailService;
import com.douzone.backend.vo.MemberVO;

@RestController("mailController")
@EnableAsync // 비동기로 호출되어서 동작함
public class MailController {

	@Autowired
	MailService mailService;

	// 회원가입 시 이메일 인증 : memberVO (email) 받아와서 status : true / false 넘겨주기
	@PostMapping("/sendCheckEmail.do")
	public Map<String, Object> sendCheckEmail(@RequestBody MemberVO memberVO) {
		Map<String, Object> result = new HashMap<>();

		String toMail = memberVO.getUseremail();

		Random random = new Random();
		String checkNum = Integer.toString(random.nextInt(888888) + 111111);

		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream("/mail/emailValidation.html"), "utf-8"));
			StringBuilder str = new StringBuilder();
			reader.lines().forEach(lineText -> {
				str.append(lineText);
			});

			String content = str.toString();
			content = content.replace("${checkNum}", checkNum);

			String title = "[SOOZDOGZ] SOOZDOGZ 회원가입 인증 이메일입니다.";
			
			mailService.sendMail(toMail, title, content);

			result.put("status", true);
			result.put("num", checkNum);
		} catch (Exception e) {
			result.put("status", false);
		}
		
		System.out.println("회원가입 이메일 인증 함 => " + result);
		return result;
	}
}
