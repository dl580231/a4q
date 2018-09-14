package com.nuc.a4q.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.entity.PageDivide;
import com.nuc.a4q.entity.Post;
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.utils.PageUtil;
import com.nuc.a4q.dao.*;

public class PostService {
	@Autowired
	private PostDao dao;
	
	/**
	 * 删除帖子
	 * 
	 * @param post
	 * @throws Exception
	 */
	public void deletePost(Post post) {
		
	}
	/**
	 * 分页查询
	 */
	public PageDivide queryPostPageList(PageDivide pageDivide, Post post) {
		if(pageDivide == null)
			pageDivide = new PageDivide();
		Integer rowCount = dao.queryPostCount();
		if(rowCount<1)
			throw new LogicException("查询数据为空");
		Integer rowStart = PageUtil.getRowStart(pageDivide.getCurrentPage(), pageDivide.getPageRowCount());
		if(rowStart>rowCount)
			throw new LogicException("查询数据为空");
		List<Post> list = dao.queryPostPageList(rowStart, pageDivide.getPageRowCount(), post);
		PageUtil.buildPageDivide(pageDivide, rowCount, pageDivide.getPageRowCount(), list);
		return pageDivide;
	}
	
}
