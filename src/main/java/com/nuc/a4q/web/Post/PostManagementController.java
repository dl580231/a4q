package com.nuc.a4q.web.Post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Post;
import com.nuc.a4q.entity.Result;
import com.nuc.a4q.entity.UserRank;
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.group.Delete;
import com.nuc.a4q.group.Update;
import com.nuc.a4q.service.PostService;
import com.nuc.a4q.utils.ResultUtil;

@Controller
@RequestMapping("/post")
public class PostManagementController {
	@Autowired
	private PostService service;

	/**
	 * 查询帖子信息
	 * 
	 * @param post
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPostList")
	public Result getPostList(Post post, Integer courseId, Integer userId) {
		if (courseId != null) {
			Course course = new Course();
			course.setCourseId(courseId);
			post.setCourse(course);
		}
		if (userId != null) {
			PersonInfo personInfo = new PersonInfo();
			personInfo.setUserId(userId);
			post.setDeployUser(personInfo);
		}
		List<Post> list = service.queryPostList(post);
		return ResultUtil.success(list);
	}

	/**
	 * 删除post信息
	 * 
	 * @param post
	 * @param bindingResult
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/removePost", method = RequestMethod.GET)
	public Result removePost(@Validated(value = Delete.class) Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new LogicException(bindingResult.getFieldError().getDefaultMessage());
		}
		service.removePost(post);
		return ResultUtil.success();
	}

	/**
	 * 帖子置顶操作，查出最小priority并减一
	 * 
	 * @param postId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/topPost", method = RequestMethod.GET)
	public Result topPost(@Validated(value = Update.class) Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new LogicException(bindingResult.getFieldError().getDefaultMessage());
		}
		service.topPost(post);
		return ResultUtil.success();
	}

	/**
	 * 帖子置顶操作，查出最小priority并减一
	 * 
	 * @param postId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bottomPost", method = RequestMethod.GET)
	public Result bottomPost(@Validated(value = Update.class) Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new LogicException(bindingResult.getFieldError().getDefaultMessage());
		}
		service.bottomPost(post);
		return ResultUtil.success();
	}

	/**
	 * 通过优先级获得已解决问题的列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getResolvedPost", method = RequestMethod.GET)
	public Result getResolvedPost() {
		List<Post> list = service.getResolvedPost();
		return ResultUtil.success(list);
	}

	/**
	 * 通过优先级获得未问题的列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getUnResolvedPost", method = RequestMethod.GET)
	public Result getUnResolvedPost() {
		List<Post> list = service.getUnResolvedPost();
		return ResultUtil.success(list);
	}

	@ResponseBody
	@RequestMapping(value = "getUserRank", method = RequestMethod.GET)
	public Result getUserRank() {
		List<UserRank> userList = service.getUserRank();
		return ResultUtil.success(userList);
	}
}
