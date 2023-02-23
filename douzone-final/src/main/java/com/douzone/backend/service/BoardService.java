package com.douzone.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.backend.dao.BoardDAO;
import com.douzone.backend.vo.BoardVO;


@Service("boardService")
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO;

	public List<BoardVO> getBoardList(String bcategory) {
		return boardDAO.getBoardList(bcategory);
	}

	public BoardVO getBoardVO(String bno) {
		return boardDAO.getBoardVO(bno);
	}

	public void boardDelete(String bno) {
		boardDAO.boardDelete(bno);
	}

	public int boardInsert(BoardVO boardVO) {
		return boardDAO.boardInsert(boardVO);
	}

	public void boardUpdate(BoardVO boardVO) {
		boardDAO.boardUpdate(boardVO);
	}

}
