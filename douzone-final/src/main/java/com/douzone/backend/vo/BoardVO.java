package com.douzone.backend.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardVO {
	private String bno;
	private String btitle;
	private String bwriter;
	private String bdate;
	private String bcontents;
	private String bcategory;
}
