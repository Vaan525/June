package edu.bit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import edu.bit.board.vo.BoardVO;
import edu.bit.board.dao.*;

@Service("boardService") // 비즈니스 로직, 커멘드 객체
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	

	@Override
	public BoardVO getBoardVO(String bId) {
		return boardDAO.contentView(bId);
	}


	@Override
	public void delBoardVO(String bId) {
		boardDAO.delete(bId);
	}


	@Override
	public List<BoardVO> BoardList() {
		return boardDAO.getBoardList();
	}


	@Override
	public void toWrite(String bName, String bTitle, String bContent) {
		boardDAO.write(bName, bTitle, bContent);
	}


	@Override
	public void toModify(String bId, String bName, String bTitle, String bContent) {
		boardDAO.modify(bId, bName, bTitle, bContent);
	}


	@Override
	public BoardVO toReply_view(String str) {
		return boardDAO.reply_view(str);
	}


	@Override
	public void toReply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep,
			String bIndent) {
		boardDAO.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
	}
	
}
