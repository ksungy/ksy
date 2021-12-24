package com.qtqt.mvc.mypage.model.dao;

import static com.qtqt.mvc.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qtqt.mvc.mypage.model.vo.Board;
import com.qtqt.mvc.common.util.PageInfo;

public class BoardDao {

	public List<Board> findBoardById(Connection connection, PageInfo pageInfo, String id) {
		List<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = 
				"SELECT RNUM, BOARD_NO, BOARD_TITLE, USER_ID, BOARD_CREATED, ORIGINAL_FILENAME, BOARD_HITS "
                + "FROM ("
				+ "SELECT ROWNUM AS RNUM, "
				+         "BOARD_NO, "
                +         "BOARD_TITLE, " 
				+		   "USER_ID, " 
				+ 			"BOARD_CREATED, " 
				+			"ORIGINAL_FILENAME, " 
				+  			"BOARD_HITS " 
                + "FROM ("
				+ 	    "SELECT B.BOARD_NO, "
				+ 			   "B.BOARD_TITLE, "
				+  			   "M.USER_ID, "
				+ 			   "B.BOARD_CREATED, " 
				+			   "B.ORIGINAL_FILENAME, " 
				+ 			   "B.BOARD_HITS "
				+ 		"FROM BOARD B " 
				+ 		"JOIN QT_USER M ON(B.USER_ID = M.USER_ID) " 
				+ 		"ORDER BY B.BOARD_NO DESC "
				+ 	 ") WHERE USER_ID = ?"
				+ ") WHERE RNUM BETWEEN ? and ?";
				
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, pageInfo.getStartList());
			pstmt.setInt(3, pageInfo.getEndList());
			
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

	public int getBoardCountById(Connection connection, String id) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM BOARD WHERE USER_ID = ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, id);
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
}