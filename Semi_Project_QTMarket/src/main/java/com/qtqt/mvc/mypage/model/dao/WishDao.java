package com.qtqt.mvc.mypage.model.dao;

import static com.qtqt.mvc.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qtqt.mvc.common.util.PageInfo;
import com.qtqt.mvc.mypage.model.vo.GoodsBoard;
import com.qtqt.mvc.mypage.model.vo.GoodsWish;

public class WishDao {

	public int getWishCountById(Connection connection, String id) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM GOODS_WISHLIST WHERE USER_ID = ?";
		
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

	public List<GoodsWish> findWishById(Connection connection, PageInfo pageInfo, String id) {
		List<GoodsWish> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = 
				"SELECT RNUM, G_LIST_NO, G_PRODUCT, G_PRODUCT_NAME, USER_ID, G_PRICE, G_DATE, G_HITS "
                + "FROM ("
				+ "SELECT ROWNUM AS RNUM, "
				+          "G_LIST_NO, "
                +          "G_PRODUCT, "
                +          "G_PRODUCT_NAME, "
                +          "USER_ID, "
                +          "G_PRICE, "
                +          "G_DATE, "
                +          "G_HITS " 
                + "FROM ("
				+ 	    "SELECT W.G_LIST_NO, "
				+ 			   "W.G_PRODUCT, "
                +               "G.G_PRODUCT_NAME, "
				+  			   "M.USER_ID, "
				+ 			   "G.G_PRICE, "
				+			   "G.G_DATE, "
				+ 			   "G.G_HITS "
				+ 		"FROM GOODS_WISHLIST W "
				+ 		"JOIN QT_USER M ON(W.USER_ID = M.USER_ID) " 
                +        "JOIN GOODS G ON(W.G_PRODUCT = G.G_PRODUCT) "
				+ 		"ORDER BY W.G_LIST_NO DESC "
				+ 	 ")WHERE USER_ID = ?"
				+ ") WHERE RNUM BETWEEN ? AND ?";
				
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, pageInfo.getStartList());
			pstmt.setInt(3, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsWish wish = new GoodsWish();
				
				wish.setNo(rs.getInt("G_LIST_NO"));
				wish.setRowNum(rs.getInt("RNUM"));
				wish.setWriterId(rs.getString("USER_ID"));
				wish.setTitle(rs.getString("G_PRODUCT_NAME"));
				wish.setPrice(rs.getString("G_PRICE"));
				wish.setCreateDate(rs.getDate("G_DATE"));
				wish.setReadCount(rs.getInt("G_HITS"));
				
				list.add(wish);
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
