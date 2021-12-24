package com.qtqt.mvc.board.model.service;

import java.sql.Connection;
import java.util.List;

import com.qtqt.mvc.board.model.vo.Board;
import com.qtqt.mvc.board.model.vo.Reply;
import com.qtqt.mvc.board.model.dao.BoardDao;
import com.qtqt.mvc.common.util.PageInfo;

import static com.qtqt.mvc.common.jdbc.JDBCTemplate.*;

public class BoardService {
	
	private BoardDao dao = new BoardDao();

	public int getBoardCount() {
		int count = 0; 
		
		Connection connection = getConnection();
		
		count = dao.getBoardCount(connection);
		
		close(connection);
		
		return count; 
	}

	public Board findBoardbyNo(int no, boolean hasRead) {
		Board board = null;
		Connection connection = getConnection();
		
		board = dao.findBoardByNo(connection, no);
		
		// 조회수를 증가하는 로직
		if(board != null && !hasRead) {
			int result = dao.updateHits(connection, board);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
			
		}
		
		
		close(connection);
		
		return board;
	}
	
	
	public List<Board> getBoardList(PageInfo pageInfo) {
		List<Board> list = null;
		Connection connection = getConnection();
		
		list = dao.findAll(connection, pageInfo);
		
		close(connection);
		
		
		return list;
	}

	public int save(Board board) {
		int result = 0;
		
		Connection connection = getConnection();
		
		if(board.getNo() != 0) {
			result = dao.updateBoard(connection, board);
		} else {
			result = dao.insertBoard(connection, board);
		}
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
		
	}

	public int delete(int no) {
		int result = 0;
		
		Connection connection = getConnection();
		
		result = dao.updateStatus(connection, no);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int saveReply(Reply reply) {
		
		int result = 0;
		Connection connection = getConnection();
		
		result = dao.insertReply(connection, reply);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int deleteReply(int no) {
		int result = 0;
		
		Connection connection = getConnection();
		
		result = dao.updateComStatus(connection, no);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int updateReply(Reply reply) {
		
		int result = 0;
		
		Connection connection = getConnection();
		
		result = dao.updateReply(connection, reply);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		
		return result;
	}




}




