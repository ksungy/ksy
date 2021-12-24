package com.qtqt.mvc.mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qtqt.mvc.common.util.PageInfo;
import com.qtqt.mvc.mypage.model.vo.GoodsBoard;

import static com.qtqt.mvc.common.jdbc.JDBCTemplate.*;

public class GoodsDao {

	public List<GoodsBoard> findGoodsById(Connection connection, PageInfo pageInfo, String id) {
		List<GoodsBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = 
				"SELECT RNUM, G_PRODUCT, USER_ID, G_PRODUCT_NAME, G_PRICE, G_DATE, G_HITS "
                + "FROM ("
				+ "SELECT ROWNUM AS RNUM, "
				+            "G_PRODUCT, " 
                +            "USER_ID, "
                +            "G_PRODUCT_NAME, " 
                +            "G_PRICE, "
                +            "G_DATE, "
                +            "G_HITS "
                + "FROM ("
				+ 	    "SELECT G.G_PRODUCT, "
                +            "G.USER_ID, "
                +            "G.G_PRODUCT_NAME, "
                +            "G.G_PRICE, "
                +            "G.G_DATE, "
                +            "G.G_HITS "
				+ 		"FROM GOODS G " 
				+ 		"JOIN QT_USER M ON(G.USER_ID = M.USER_ID) "
				+ 		"ORDER BY G.G_PRODUCT DESC "
				+ 	 ") WHERE USER_ID = ?"
				+  ") WHERE RNUM BETWEEN ? and ?";
				
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, pageInfo.getStartList());
			pstmt.setInt(3, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsBoard board = new GoodsBoard();
				
				board.setNo(rs.getInt("G_PRODUCT"));
				board.setRowNum(rs.getInt("RNUM"));
				board.setWriterId(rs.getString("USER_ID"));
				board.setTitle(rs.getString("G_PRODUCT_NAME"));
				board.setPrice(rs.getString("G_PRICE"));
				board.setCreateDate(rs.getDate("G_DATE"));
				board.setReadCount(rs.getInt("G_HITS"));
				
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

	public int getGoodsCountById(Connection connection, String id) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM GOODS WHERE USER_ID = ?";
		
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