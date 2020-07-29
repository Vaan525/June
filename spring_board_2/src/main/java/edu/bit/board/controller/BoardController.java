package edu.bit.board.controller;

import java.text.DateFormat;


import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import edu.bit.board.service.BoardService;
import edu.bit.board.service.BoardServiceImpl;
import edu.bit.board.vo.BoardVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
		
	@RequestMapping("/list")
	public String list(Model model) {
		logger.info("list");
		
		List<BoardVO> boardVO = boardService.BoardList();
		model.addAttribute("list", boardVO);
		
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("/delete")
	public String delete(BoardVO boardVO, Model model) {
		logger.info("belete");
		String bId = String.valueOf(boardVO.getbId());
		logger.info("bId");
		boardService.delBoardVO(bId);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(BoardVO boardVO, Model model){
		System.out.println("content_view()");
		
		String bid = String.valueOf(boardVO.getbId());
		boardVO = boardService.getBoardVO(bid);
		model.addAttribute("content_view", boardVO);
		
		return "content_view";
	}
	
	@RequestMapping("/write")
	public String write(BoardVO boardVO, Model model) {
		System.out.println("write()");
		
		logger.info("write");
		String bName = String.valueOf(boardVO.getbName());
		String bTitle = String.valueOf(boardVO.getbTitle());
		String bContent = String.valueOf(boardVO.getbContent());
		
		
		boardService.toWrite(bName, bTitle, bContent);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST )
	public String modify(BoardVO boardVO, Model model){
		System.out.println("modify()");
		
		String bId = String.valueOf(boardVO.getbId());
		String bName = String.valueOf(boardVO.getbName());
		String bTitle = String.valueOf(boardVO.getbTitle());
		String bContent = String.valueOf(boardVO.getbContent());
		
		boardService.toModify(bId, bName, bTitle, bContent);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(BoardVO boardVO, Model model){
		System.out.println("reply_view");
		
		String bId = String.valueOf(boardVO.getbId());
		model.addAttribute("reply_view", boardVO);
		boardService.toReply_view(bId);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(BoardVO boardVO) {
		System.out.println("reply");
		
		String bId = String.valueOf(boardVO.getbId());
		String bName = String.valueOf(boardVO.getbName());
		String bTitle = String.valueOf(boardVO.getbTitle());
		String bContent = String.valueOf(boardVO.getbContent());
		
		String bGroup = String.valueOf(boardVO.getbGroup());
		String bStep = String.valueOf(boardVO.getbStep()+1);
		String bIndent = String.valueOf(boardVO.getbIndent()+1);
		
		
		boardService.toReply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
		
		return "redirect:list";
	}
	
	
}
