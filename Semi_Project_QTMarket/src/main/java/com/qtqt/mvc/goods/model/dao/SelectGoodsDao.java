//package com.qtqt.mvc.goods.model.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.qtqt.mvc.common.util.PageInfo;
//import com.qtqt.mvc.goods.model.vo.GoodsBoard;
//import com.qtqt.mvc.goods.model.vo.GoodsReply;
//import com.qtqt.mvc.goods.model.vo.GoodsWish;
//
//import static com.qtqt.mvc.common.jdbc.JDBCTemplate.*;
//
//public class SelectGoodsDao {
//
//	public int getBoardCount(Connection connection) {
//		
//		int count = 0;
//		PreparedStatement pstmt = conn.prepareStatement("select count(*) form market");
//		ResultSet rs = null;
//		String query = "SELECT COUNT(*) FROM GOODS";
//		
//		try {
//			pstmt = connection.prepareStatement(query);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				count = rs.getInt(1);
//			} 
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(pstmt);
//		}
//		
//		return count;
//	}
//}