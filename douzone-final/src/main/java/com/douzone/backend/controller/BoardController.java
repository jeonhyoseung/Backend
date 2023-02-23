package com.douzone.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.backend.service.BoardService;
import com.douzone.backend.vo.BoardVO;

@RestController
public class BoardController {

	@Autowired
	BoardService boardService;

	// 게시글 목록 받아오기
	@PostMapping("/boardList.do")
	public Map<String, Object> boardList(@RequestBody BoardVO boardVO) {
		Map<String, Object> result = new HashMap<>();
		
		String bcategory = boardVO.getBcategory();
		if (boardVO.getBcategory() == null ) {
			bcategory = "%";
		}

		try {
			List<BoardVO> boardList = boardService.getBoardList(bcategory);
			result.put("boardList", boardList);
			result.put("status", true);
		} catch (Exception e) {
			result.put("status", false);
		}
		
		System.out.println("게시글 목록 가져옴 => " + result);
		return result;
	}
	
	// 게시글 요소 가져오기
	@PostMapping("/boardView.do")
	public Map<String, Object> boardView(@RequestBody BoardVO boardVO) {
		Map<String, Object> result = new HashMap<>();

		try {
			BoardVO board = boardService.getBoardVO(boardVO.getBno());
			result.put("boardVO", board);
			result.put("status", true);
		} catch (Exception e) {
			result.put("status", false);
		}
		
		System.out.println("게시글 요소 가져옴 => " + result);
		return result;
	}
	
	// 게시글 삭제하기
	@PostMapping("/boardDelete.do")
	public Map<String, Object> boardDelete(@RequestBody BoardVO boardVO) {
		Map<String, Object> result = new HashMap<>();

		try {
			boardService.boardDelete(boardVO.getBno());
			result.put("status", true);
		} catch (Exception e) {
			result.put("status", false);
		}
		
		System.out.println("게시글 삭제함 => " + result);
		return result;
	}
	
	// 게시글 등록하기 
	@PostMapping("/boardInsert.do")
	public Map<String, Object> boardInsert(@RequestBody BoardVO boardVO) {
		Map<String, Object> result = new HashMap<>();

		try {
			boardService.boardInsert(boardVO);
			
			result.put("bno", boardVO.getBno());
			result.put("status", true);
		} catch (Exception e) {
			result.put("status", false);
		}
		
		System.out.println("게시글 등록함 => " + result);
		return result;
	}
	
	// 게시글 수정하기 
	@PostMapping("/boardUpdate.do")
	public Map<String, Object> boardUpdate(@RequestBody BoardVO boardVO) {
		Map<String, Object> result = new HashMap<>();

		try {
			boardService.boardUpdate(boardVO);
			result.put("status", true);
		} catch (Exception e) {
			result.put("status", false);
		}
		
		System.out.println("게시글 등록함 => " + result);
		return result;
	}
}
