package com.it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.it.domain.BoardVO;
import com.it.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/")
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model) {	// 객체를 저장해서 jsp 파일로 전송
		// list.jsp에 데이터를 전달
		model.addAttribute("list", service.getList());	// getList로 조회한 내용을 리스트 변수로 전달model 객체
	}
	
	@GetMapping("/insert")
	public void insert() {
		// 페이지를 호출만 함
	}
	
	@PostMapping("/insert")
	public String insert(BoardVO board) {
		log.info(board);
		service.insert(board);
		return "redirect:/board/list";
	}
	
	@GetMapping("/read")
	public void read(Model model, BoardVO board) {
		log.info(board);
		board = service.read(board);
		log.info(board);
		model.addAttribute("board", board);
	}
	
	@GetMapping("/update")
	public void update(Model model, @RequestParam("b_num") int b_num) {
		BoardVO board = new BoardVO();
		log.info(board);
		board.setB_num(b_num);
		model.addAttribute("board", service.read(board));
	}
	
	@PostMapping("/update")
	public String update(BoardVO board) {
		log.info(board);
		service.update(board);
		return "redirect:/board/read?b_num=" + board.getB_num();
	}
	
	@GetMapping("/delete")
	public String delete(BoardVO board) {
		service.delete(board);
		return "redirect:/board/list";
	}
}
