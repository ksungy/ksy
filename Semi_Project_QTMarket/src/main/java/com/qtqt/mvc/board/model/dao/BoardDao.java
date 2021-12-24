package com.qtqt.mvc.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qtqt.mvc.board.model.vo.Board;
import com.qtqt.mvc.board.model.vo.Reply;
import com.qtqt.mvc.common.util.PageInfo;

import static com.qtqt.mvc.common.jdbc.JDBCTemplate.*;

public class BoardDao {


	public int getBoardCount(Connection connection) {
		
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM BOARD";
		
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

	public Board findBoardByNo(Connection connection, int no) {
		Board board = new Board();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = 
				"SELECT "
				+		"B.BOARD_NO, "
				+		"B.BOARD_TITLE, "
				+ 		"M.USER_ID, "
				+		"B.BOARD_HITS, "
				+		"B.ORIGINAL_FILENAME, "
				+		"B.RENAMED_FILENAME, "
				+ 		"B.BOARD_CONTENT, "
				+		"B.BOARD_CREATED, "
				+ 		"B.BOARD_MODIFIED, "
				+		"B.CATEGORY "
				+ "FROM BOARD B "
				+ "JOIN QT_USER M ON(B.USER_ID = M.USER_ID) "
				+ "WHERE B.BOARD_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setNo(no);
				board.setTitle(rs.getString("BOARD_TITLE"));
				board.setWriterId(rs.getString("USER_ID"));
				board.setHits(rs.getInt("BOARD_HITS"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				board.setContent(rs.getString("BOARD_CONTENT"));
				board.setReplies(this.getRepliesByNO(connection, no));
				board.setCreateDate(rs.getDate("BOARD_CREATED"));
				board.setModifyDate(rs.getDate("BOARD_MODIFIED"));
				board.setCategory(rs.getString("CATEGORY"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}
	
	
	private List<Reply> getRepliesByNO(Connection connection, int boardNo) {
		List<Reply> replise = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT C.COMMENT_NO, C.BOARD_NO, C.COMMENT_CONTENT, M.USER_ID, C.B_COMMENT_CREATED, C.B_COMMENT_MODIFIED "
				+ "FROM BOARD_COMMENT C "
				+ "JOIN QT_USER M ON(C.USER_ID = M.USER_ID) "
				+ "WHERE BOARD_NO=? "
				+ "ORDER BY C.COMMENT_NO DESC";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
					Reply reply = new Reply();
					
					reply.setNo(rs.getInt("COMMENT_NO"));
					reply.setBoardNo(rs.getInt("BOARD_NO"));
					reply.setContent(rs.getString("COMMENT_CONTENT"));
					reply.setWriterId(rs.getString("USER_ID"));
					reply.setCreateDate(rs.getDate("B_COMMENT_CREATED"));
					reply.setModifyDate(rs.getDate("B_COMMENT_MODIFIED"));;
				
					replise.add(reply);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return replise;
	}

	public List<Board> findAll(Connection connection, PageInfo pageInfo) {
		List<Board> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = 
							"SELECT RNUM, BOARD_NO, BOARD_TITLE, USER_ID, BOARD_CREATED, ORIGINAL_FILENAME, BOARD_HITS, CATEGORY "
							+ "FROM ("
							+    "SELECT ROWNUM AS RNUM, "
							+           "BOARD_NO, "
							+ 			"BOARD_TITLE, "
							+ 			"USER_ID, "
							+ 			"BOARD_CREATED, "
							+			"ORIGINAL_FILENAME, "
							+  			"BOARD_HITS, "
							+			"CATEGORY "
							+ 	 "FROM ("
							+ 	    "SELECT B.BOARD_NO, "
							+ 			   "B.BOARD_TITLE, "
							+  			   "M.USER_ID, "
							+ 			   "B.BOARD_CREATED, "
							+			   "B.ORIGINAL_FILENAME, "
							+ 			   "B.BOARD_HITS, "
							+			   "B.CATEGORY "
							+ 		"FROM BOARD B "
							+ 		"JOIN QT_USER M ON(B.USER_ID = M.USER_ID) "
							+ 		"ORDER BY B.BOARD_NO DESC"
							+ 	 ")"
							+ ") WHERE RNUM BETWEEN ? and ?";
				
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setNo(rs.getInt("BOARD_NO"));
				board.setRowNum(rs.getInt("RNUM"));
				board.setWriterId(rs.getString("USER_ID"));
				board.setTitle(rs.getString("BOARD_TITLE"));
				board.setCreateDate(rs.getDate("BOARD_CREATED"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setHits(rs.getInt("BOARD_HITS"));
				board.setCategory(rs.getString("CATEGORY"));
				
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

	public int insertBoard(Connection connection, Board board) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL,?,?,?,DEFAULT,DEFAULT,DEFAULT,?,?,?)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, board.getWriterId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getOriginalFileName());
			pstmt.setString(5, board.getRenamedFileName());
			pstmt.setString(6, board.getCategory());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		return result;
	}

	public int updateStatus(Connection connection, int no) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "DELETE FROM BOARD WHERE BOARD_NO=?";
		
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


	public int updateBoard(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET BOARD_TITLE=?,BOARD_CONTENT=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?,CATEGORY=?,BOARD_MODIFIED=SYSDATE WHERE BOARD_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getOriginalFileName());
			pstmt.setString(4, board.getRenamedFileName());
			pstmt.setString(5, board.getCategory());
			pstmt.setInt(6, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	public int updateHits(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET BOARD_HITS=? WHERE BOARD_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			board.setHits(board.getHits() + 1);
			
			pstmt.setInt(1, board.getHits());
			pstmt.setInt(2, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	public int insertReply(Connection connection, Reply reply) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO BOARD_COMMENT VALUES(SEQ_COMMENT_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, reply.getBoardNo());
			pstmt.setString(2, reply.getWriterId());
			pstmt.setString(3, reply.getContent());
			
			result = pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateComStatus(Connection connection, int no) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "DELETE FROM BOARD_COMMENT WHERE COMMENT_NO=?";
		
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

	public int updateReply(Connection connection, Reply reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD_COMMENT SET COMMENT_CONTENT=? WHERE COMMENT_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, reply.getContent());
			pstmt.setInt(2, reply.getNo());
			
			result = pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}




}
