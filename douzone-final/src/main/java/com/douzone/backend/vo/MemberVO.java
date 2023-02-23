package com.douzone.backend.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberVO {
	private String userid;
	private String username;
	private String userpwd;
	private String useremail;
	private String userphone;
	private String userbirth;
}
