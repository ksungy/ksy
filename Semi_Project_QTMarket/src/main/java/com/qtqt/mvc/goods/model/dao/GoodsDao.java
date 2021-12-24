package com.qtqt.mvc.goods.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qtqt.mvc.common.util.PageInfo;
import com.qtqt.mvc.goods.model.vo.GoodsBoard;
import com.qtqt.mvc.goods.model.vo.GoodsReply;
import com.qtqt.mvc.goods.model.vo.GoodsWish;


import static com.qtqt.mvc.common.jdbc.JDBCTemplate.*;

public class GoodsDao {

	public int getBoardCount(Connection connection) {
		
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM GOODS";
		
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return count;
	}

	public int updateBoard(Connection connection, GoodsBoard board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE GOODS SET G_PRODUCT_NAME=?,G_PRICE=?,G_CONTENT=?,G_CATE=?,G_O_FILENAME=?,G_R_FILENAME=?,G_AREA1=?,G_AREA2=? WHERE G_PRODUCT=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getPrice());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getCate());
			pstmt.setString(5, board.getOriginalFileName());
			pstmt.setString(6, board.getRenamedFileName());
			pstmt.setString(7, board.getArea1());
			pstmt.setString(8, board.getArea2());
			pstmt.setInt(9, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertBoard(Connection connection, GoodsBoard board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO GOODS VALUES(SEQ_GOODS_NO.NEXTVAL,?,?,?,?,DEFAULT,DEFAULT,?,?,?,?,?,DEFAULT)";

		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, board.getWriterId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getPrice());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getCate());
			pstmt.setString(6, board.getOriginalFileName());
			pstmt.setString(7, board.getRenamedFileName());
			pstmt.setString(8, board.getArea1());
			pstmt.setString(9, board.getArea2());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<GoodsBoard> findAll(Connection connection, PageInfo pageInfo) {
		List<GoodsBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = 
				"SELECT ROWNUM, G_PRODUCT, USER_ID, G_PRODUCT_NAME, G_PRICE, G_CONTENT, G_DATE, G_HITS, G_CATE, G_O_FILENAME, G_R_FILENAME, G_AREA1, G_AREA2, STATUS "
				+ "FROM GOODS "
				+ "WHERE ROWNUM BETWEEN ? and ? "
				+ "ORDER BY G_PRODUCT DESC";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsBoard board = new GoodsBoard();
				
				board.setNo(rs.getInt("G_PRODUCT"));
				board.setRowNum(rs.getInt("ROWNUM"));
				board.setWriterId(rs.getString("USER_ID"));
				board.setTitle(rs.getString("G_PRODUCT_NAME"));
				board.setPrice(rs.getString("G_PRICE"));
				board.setContent(rs.getString("G_CONTENT"));
				board.setCreateDate(rs.getDate("G_DATE"));
				board.setReadCount(rs.getInt("G_HITS"));
				board.setCate(rs.getString("G_CATE"));
				board.setOriginalFileName(rs.getString("G_O_FILENAME"));
				board.setRenamedFileName(rs.getString("G_R_FILENAME"));
				board.setArea1(rs.getString("G_AREA1"));
				board.setArea2(rs.getString("G_AREA2"));

				board.setStatus(rs.getString("STATUS"));

				
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return list;
	}

	public int updateReadCount(Connection connection, GoodsBoard board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE GOODS SET G_HITS=? WHERE G_PRODUCT=?";

		
		try {
			pstmt = connection.prepareStatement(query);
			
			board.setReadCount(board.getReadCount() + 1);
			
			pstmt.setInt(1, board.getReadCount());
			pstmt.setInt(2, board.getNo());
			
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		
		return result;
	}

	public GoodsBoard findBoardByNo(Connection connection, int no) {
		GoodsBoard board = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = 
				"SELECT "
				+ 	"G_PRODUCT, "
				+   "USER_ID, "
				+   "G_PRODUCT_NAME, "
				+   "G_PRICE, "
				+   "G_CONTENT, "
				+   "G_DATE, "
				+   "G_HITS, "
				+   "G_CATE, "
				+   "G_O_FILENAME, "
				+   "G_R_FILENAME, "
				+	"STATUS "
				+ "FROM GOODS "
				+ "WHERE G_PRODUCT=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				board = new GoodsBoard();
				
				board.setNo(rs.getInt("G_PRODUCT"));
				board.setWriterId(rs.getString("USER_ID"));
				board.setTitle(rs.getString("G_PRODUCT_NAME"));
				board.setPrice(rs.getString("G_PRICE"));
				board.setContent(rs.getString("G_CONTENT"));
				board.setCreateDate(rs.getDate("G_DATE"));
				board.setReadCount(rs.getInt("G_HITS"));
				board.setCate(rs.getString("G_CATE"));
				board.setReplies(this.getRepliesByNo(connection, no));
				board.setOriginalFileName(rs.getString("G_O_FILENAME"));
				board.setRenamedFileName(rs.getString("G_R_FILENAME"));
				board.setStatus(rs.getString("STATUS"));

			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}

	public int updateStatus(Connection connection, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM GOODS WHERE G_PRODUCT=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertReply(Connection connection, GoodsReply reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO GOODS_COMMENT VALUES(SEQ_REPLY_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, reply.getBoardNo());
			pstmt.setString(2, reply.getContent());
			pstmt.setString(3, reply.getWriterId());
			
			result = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<GoodsReply> getRepliesByNo(Connection connection, int boardNo) {
		List<GoodsReply> replies = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		
		try {
			query = 
				  "SELECT G_COMMENT_NO, G_PRODUCT, G_BOARD_CONTENT, G_WRITER_ID, G_COMMENT_CREATED, G_COMMENT_MODIFIED "
				+ "FROM GOODS_COMMENT "
				+ "WHERE G_PRODUCT=? "
				+ "ORDER BY G_COMMENT_NO DESC";
			
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsReply reply = new GoodsReply();
				
				reply.setNo(rs.getInt("G_COMMENT_NO"));
				reply.setBoardNo(rs.getInt("G_PRODUCT"));
				reply.setContent(rs.getString("G_BOARD_CONTENT"));
				reply.setWriterId(rs.getString("G_WRITER_ID"));
				reply.setCreateDate(rs.getDate("G_COMMENT_CREATED"));
				reply.setModifyDate(rs.getDate("G_COMMENT_MODIFIED"));
				
				replies.add(reply);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}		
		
		return replies;
	}

	public int updateComStatus(Connection connection, int no) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "DELETE FROM GOODS_COMMENT WHERE G_COMMENT_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int complete(Connection connection, int no) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "UPDATE GOODS SET STATUS='N' WHERE G_PRODUCT=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int goodswish(Connection connection, GoodsWish wish) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO GOODS_WISHLIST VALUES(?, ?)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, wish.getNo());
			pstmt.setString(2, wish.getWriterId());
			
			result = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int goodswishcount(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM GOODS_WISHLIST";
		
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return count;
	}

	public List<GoodsWish> goodswishlist(Connection connection, PageInfo pageInfo, GoodsWish wish) {
		List<GoodsWish> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = 
				"SELECT ROWNUM, W.G_PRODUCT, W.USER_ID, G.G_PRODUCT_NAME, G.G_PRICE, G.G_CONTENT, G.G_DATE, G.G_HITS, G.G_CATE, G.G_O_FILENAME, G.G_R_FILENAME, G.G_AREA1, G.G_AREA2, STATUS "
		                  + "FROM GOODS_WISHLIST W "
		                  + "JOIN GOODS G ON(G.G_PRODUCT = W.G_PRODUCT) "
		                  + "WHERE ROWNUM BETWEEN ? and ? and W.USER_ID=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			pstmt.setString(3, wish.getWriterId());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsWish board = new GoodsWish();
				
				board.setNo(rs.getInt("G_PRODUCT"));
				board.setRowNum(rs.getInt("ROWNUM"));
				board.setWriterId(rs.getString("USER_ID"));
				board.setTitle(rs.getString("G_PRODUCT_NAME"));
				board.setPrice(rs.getString("G_PRICE"));
				board.setContent(rs.getString("G_CONTENT"));
				board.setCreateDate(rs.getDate("G_DATE"));
				board.setReadCount(rs.getInt("G_HITS"));
				board.setCate(rs.getString("G_CATE"));
				board.setOriginalFileName(rs.getString("G_O_FILENAME"));
				board.setRenamedFileName(rs.getString("G_R_FILENAME"));
				board.setArea1(rs.getString("G_AREA1"));
				board.setArea2(rs.getString("G_AREA2"));
				board.setStatus(rs.getString("STATUS"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return list;
	}

	public List<GoodsBoard> noticelist(Connection connection, String field, String field2, PageInfo pageInfo) {
		List<GoodsBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = 
				"SELECT ROWNUM, G_PRODUCT, USER_ID, G_PRODUCT_NAME, G_PRICE, G_CONTENT, G_DATE, G_HITS, G_CATE, G_O_FILENAME, G_AREA1, G_AREA2, STATUS "
				+ "FROM GOODS "
				+ "WHERE ROWNUM BETWEEN ? and ? and ? LIKE ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			pstmt.setString(3, field);
			pstmt.setString(4, field2);
			
			System.out.println(field);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsBoard board = new GoodsBoard();
				
				board.setNo(rs.getInt("G_PRODUCT"));
				board.setRowNum(rs.getInt("ROWNUM"));
				board.setWriterId(rs.getString("USER_ID"));
				board.setTitle(rs.getString("G_PRODUCT_NAME"));
				board.setPrice(rs.getString("G_PRICE"));
				board.setContent(rs.getString("G_CONTENT"));
				board.setCreateDate(rs.getDate("G_DATE"));
				board.setReadCount(rs.getInt("G_HITS"));
				board.setCate(rs.getString("G_CATE"));
				board.setOriginalFileName(rs.getString("G_O_FILENAME"));
				board.setArea1(rs.getString("G_AREA1"));
				board.setArea2(rs.getString("G_AREA2"));
				board.setStatus(rs.getString("STATUS"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return list;
	}

}