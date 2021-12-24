package com.qtqt.mvc.mypage.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsWish {
	private int no;
	
	private int rowNum;
	
	private int writerNo;
	
	private String writerId;
	
	private String title;
	
	private String content;
	
	private String originalFileName;
	
	private String renamedFileName;
	
	private int readCount;
	
	private String status;
	
	private Date createDate;
	
	private Date modifyDate;
	
	private String price;
	
	private String cate;
	
	private String area1;
	
	private String area2;

}