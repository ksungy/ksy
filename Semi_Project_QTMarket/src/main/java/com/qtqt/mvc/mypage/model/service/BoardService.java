package com.qtqt.mvc.mypage.model.service;

import java.sql.Connection;
import java.util.List;

import com.qtqt.mvc.mypage.model.dao.BoardDao;
import com.qtqt.mvc.mypage.model.vo.Board;
import com.qtqt.mvc.common.util.PageInfo;

import static com.qtqt.mvc.common.jdbc.JDBCTemplate.*;

public class BoardService {
	
	private BoardDao dao = new BoardDao();

	public List<Board> getBoardListById(PageInfo pageInfo, String id) {
		List<Board> list = null;
		Connection connection = getConnection();
		
		list = dao.findBoardById(connection, pageInfo, id);
		
		close(connection);
		
		return list;
	}

	public int getBoardCountById(String id) {
		int count = 0; 
		
		Connection connection = getConnection();
		
		count = dao.getBoardCountById(connection, id);
		
		close(connection);
		
		return count;
	}

}