package com.qtqt.mvc.mypage.model.service;

import static com.qtqt.mvc.common.jdbc.JDBCTemplate.close;
import static com.qtqt.mvc.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.qtqt.mvc.common.util.PageInfo;
import com.qtqt.mvc.mypage.model.dao.WishDao;
import com.qtqt.mvc.mypage.model.vo.GoodsWish;

public class WishService {
	
	private WishDao dao = new WishDao();
	
	public int getWishCountById(String id) {
		int count = 0; 
		
		Connection connection = getConnection();
		
		count = dao.getWishCountById(connection, id);
		
		close(connection);
		
		return count;
	}

	public List<GoodsWish> getWishListById(PageInfo pageInfo, String id) {
		List<GoodsWish> list = null;
		Connection connection = getConnection();
		
		list = dao.findWishById(connection, pageInfo, id);
		
		close(connection);
		
		return list;
	}

}
