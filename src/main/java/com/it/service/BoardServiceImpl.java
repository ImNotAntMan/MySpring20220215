package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.domain.BoardVO;
import com.it.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Override
	public BoardVO read(BoardVO board) {
		return mapper.read(board);
	}
	
	@Override
	public BoardVO read(int b_num) {
		return mapper.read(b_num);
	}
	
	@Override
	public void insert(BoardVO board) {
		mapper.insert(board);
	}
	
	@Override
	public List<BoardVO> getList() {
		return mapper.getList();
	}
	
	@Override
	public void update(BoardVO board) {
		mapper.update(board);
	}
	
	@Override
	public void delete(BoardVO board) {
		mapper.delete(board);
	}
}
