package com.nuc.a4q.dao;


import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.Floor;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Post;

public class PostDaoTest extends BaseTest {
	@Autowired
	private PostDao postdao;
	
	@Test
	@Ignore
	public void insertPostTest() {
		Post post = new Post();
		Course course = new Course();
		Floor flo = new Floor();
		course.setCourseId(3);
		post.setCourse(course);
		PersonInfo per = new PersonInfo();
		per.setUserId(1);
		post.setDeployUser(per);
		flo.setFloorId(null);
		post.setBestAnswer(flo);
		post.setPostContent("qddwefqwrgr");
		post.setPriority(null);
		post.setEnableView(null);
		post.setCreateTime(new Date());
		post.setLastEditTime(new Date());
		Integer integer = postdao.insertPost(post);
        System.out.println(integer);
	}
	
	@Test
	@Ignore
	public void updatepostTest() {
		Post post = new Post();
		post.setPostId(1);
		Course course =new Course();
		course.setCourseId(16);
		post.setCourse(course);
		PersonInfo per = new PersonInfo();
		per.setUserId(3);
		post.setDeployUser(per);
		Floor flo = new Floor();
		flo.setFloorId(2);
		post.setBestAnswer(flo);
		post.setPostTitle("这是一个问题");
		post.setPostContent("操作系统（Operating System，简称OS）是管理和控制计算机硬件与软件资源的");
		post.setPriority(1);
		post.setEnableView(0);
		post.setCreateTime(new Date());
		post.setLastEditTime(new Date());
		Integer integer = postdao.updatePost(post);
		System.out.println(integer);
	}
	
	@Test
	@Ignore
	public void deletePostTest() {
		Post post = new Post();
		post.setPostId(2);
		Integer integer = postdao.deletePost(post);
		System.out.println(integer);
	}
	
	@Test
	public void queryPostListTest() {
		Post post = new Post();
		//Course course = new Course();
		//course.setCourseId(16);
		//post.setCourse(course);
		post.setPostContent("操作");
		List<Post> list = postdao.queryPostList(post);
		System.out.println(list);
	}
}
