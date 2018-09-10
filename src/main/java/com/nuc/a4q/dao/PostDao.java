package com.nuc.a4q.dao;

import java.util.List;

import com.nuc.a4q.entity.Post;

public interface PostDao {
	/**
	 * 增加帖子
	 * 
	 * @return
	 */
	public Integer insertPost(Post post);
	
	/**
	 * 修改帖子
	 * 
	 * @param course
	 * @return 
	 */
	public Integer updatePost(Post post);
	
	/**
	 * 删除帖子
	 * @param course
	 * @return
	 */
	public Integer deletePost(Post post);

	
	/**
	 * 模糊查询
	 */
	public List<Post> queryPostList(Post post);
	
}
