package com.nuc.a4q.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuc.a4q.dao.PostDao;
import com.nuc.a4q.entity.Post;
import com.nuc.a4q.entity.UserRank;

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

	/**
	 * 根据课程ID
	 * 
	 * @param courseId
	 */
	public Integer getPostCountByCourseId(Integer courseId) {
		return dao.getPostCountByCourseId(courseId);
	}

	/**
	 * 通过优先级获得已解决的问题列表
	 * 
	 * @return
	 */
	public List<Post> getResolvedPost() {
		List<Post> list = dao.getResolvedPostTestByPriority();
		return list;
	}

	/**
	 * 通过优先级获得未解决的问题列表
	 * 
	 * @return
	 */
	public List<Post> getUnResolvedPost() {
		List<Post> list = dao.getUnResolvedPostTestByPriority();
		return list;
	}

	/**
	 * 通过用户回答的问题的评价获得排序
	 * @return
	 */
	public List<UserRank> getUserRank() {
		List<UserRank> userList = dao.getUserRank();
		return userList;
	}
}
