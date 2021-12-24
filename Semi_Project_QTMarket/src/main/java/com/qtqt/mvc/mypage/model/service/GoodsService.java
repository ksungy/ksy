package com.qtqt.mvc.mypage.model.service;

import static com.qtqt.mvc.common.jdbc.JDBCTemplate.close;
import static com.qtqt.mvc.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.qtqt.mvc.common.util.PageInfo;
import com.qtqt.mvc.mypage.model.dao.GoodsDao;
import com.qtqt.mvc.mypage.model.vo.GoodsBoard;

public class GoodsService {
	
	private GoodsDao dao = new GoodsDao();

	public List<GoodsBoard> getGoodsListById(PageInfo pageInfo, String id) {
		List<GoodsBoard> list = null;
		Connection connection = getConnection();
		
		list = dao.findGoodsById(connection, pageInfo, id);
		
		close(connection);
		
		return list;
	}

	public int getGoodsCountById(String id) {
		int count = 0; 
		
		Connection connection = getConnection();
		
		count = dao.getGoodsCountById(connection, id);
		
		close(connection);
		
		return count;
	}

	

}