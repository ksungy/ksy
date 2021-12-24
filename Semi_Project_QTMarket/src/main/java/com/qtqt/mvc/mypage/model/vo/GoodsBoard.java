package com.qtqt.mvc.mypage.model.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsBoard {
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
	
	private List<GoodsReply> replies;
	
	private Date createDate;
	
	private Date modifyDate;
	
	private String price;
	
	private String cate;
	
	private String area1;
	
	private String area2;
	
}
