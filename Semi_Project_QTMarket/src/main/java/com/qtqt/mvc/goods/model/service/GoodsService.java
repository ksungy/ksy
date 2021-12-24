package com.qtqt.mvc.goods.model.service;

import static com.qtqt.mvc.common.jdbc.JDBCTemplate.close;
import static com.qtqt.mvc.common.jdbc.JDBCTemplate.commit;
import static com.qtqt.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.qtqt.mvc.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.qtqt.mvc.common.util.PageInfo;
import com.qtqt.mvc.goods.model.dao.GoodsDao;
import com.qtqt.mvc.goods.model.vo.GoodsBoard;
import com.qtqt.mvc.goods.model.vo.GoodsReply;
import com.qtqt.mvc.goods.model.vo.GoodsWish;


public class GoodsService {
	
	private GoodsDao dao = new GoodsDao();

	public int getBoardCount() {
		int count = 0; 
		
		Connection connection = getConnection();
		
		count = dao.getBoardCount(connection);
		
		close(connection);
		
		return count;
	}
	
	public GoodsBoard findBoardByNo(int no) {
		GoodsBoard board = null;
		Connection connection = getConnection();
		
		board = dao.findBoardByNo(connection, no);
		
		close(connection);
		
		return board;
		
	}


	public int save(GoodsBoard board) {
		int result = 0;
		Connection connection = getConnection();
		
		if (board.getNo() != 0 ) {
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

	public List<GoodsBoard> getBoardList(PageInfo pageInfo) {
		List<GoodsBoard> list = null;
		Connection connection = getConnection();
		
		list = dao.findAll(connection, pageInfo);
		
		close(connection);
		
		return list;
	}

	public GoodsBoard findBoardByNo(int no, boolean hasRead) {
		GoodsBoard board = null;
		Connection connection = getConnection();
		
		board = dao.findBoardByNo(connection, no);
		
		// 게시글 조회수를 증가시키는 로직
		if(board != null && !hasRead) {
			int result = dao.updateReadCount(connection, board);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
		}
		
		close(connection);		
		
		return board;
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

	public int saveReply(GoodsReply reply) {
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

	public int complete(int no) {
		int result = 0;
		
		Connection connection = getConnection();
		
		result = dao.complete(connection, no);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int goodswish(GoodsWish wish) {
		int result = 0;
		Connection connection = getConnection();
		
		result = dao.goodswish(connection, wish);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int goodswishcount() {
		int count = 0; 
		
		Connection connection = getConnection();
		
		count = dao.goodswishcount(connection);
		
		close(connection);
		
		return count;
	}

	public List<GoodsWish> goodswishlist(PageInfo pageInfo, GoodsWish wish) {
		List<GoodsWish> list = null;
		Connection connection = getConnection();
		
		list = dao.goodswishlist(connection, pageInfo, wish);
		
		close(connection);
		
		return list;
	}

	public List<GoodsBoard> noticelist(String field, String field2, PageInfo pageInfo) {
		List<GoodsBoard> list = null;
		Connection connection = getConnection();
		
		list = dao.noticelist(connection, field, field2, pageInfo);
		
		close(connection);
		
		return list;
	}

}