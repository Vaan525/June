package edu.bit.board.service;

import java.util.List;


import org.springframework.ui.Model;

import edu.bit.board.vo.BoardVO;

public interface BoardService {
	
	List<BoardVO> BoardList();
	
	BoardVO toReply_view(String str);
	
	void toReply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent);
	
	BoardVO getBoardVO(String bid);

	void delBoardVO(String bId);
	
	void toWrite(String bName, String bTitle, String bContent);
	
	void toModify(String bId, String bName, String bTitle, String bContent);
	
}
