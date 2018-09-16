package com.nuc.a4q.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuc.a4q.dao.PostDao;
import com.nuc.a4q.entity.Post;

@Service
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
	 * 查询帖子信息
	 */
	public List<Post> queryPostList(Post post) {
		List<Post> list = dao.queryPostList(post);
		return list;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param postId
	 */
	public void removePost(Post post) {
		dao.deletePost(post);
	}

	/**
	 * 帖子置顶操作
	 * 
	 * @param post
	 */
	@Transactional
	public void topPost(Post post) {
		Integer minPriority = dao.queryMinPriority();
		post.setPriority(minPriority - 1);
		dao.updatePost(post);
	}

	/**
	 * 帖子置底操作
	 * 
	 * @param post
	 */
	@Transactional // 这里加上事务，解决不可重复读问题
	public void bottomPost(Post post) {
		Integer maxPriority = dao.queryMaxPriority();
		post.setPriority(maxPriority + 1);
		dao.updatePost(post);
	}

}
