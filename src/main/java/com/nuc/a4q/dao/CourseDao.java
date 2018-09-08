package com.nuc.a4q.dao;

import com.nuc.a4q.entity.Course;

public interface CourseDao {
	/**
	 * 增加课程
	 * 
	 * @return
	 */
	public Integer insertCourse(Course course);
	/**
	 * 删除课程
	 * 
	 * @param course
	 * @return 
	 */
	public Integer deleteCourse(Course course);
	/**
	 * 删除课程
	 * 
	 * @param course
	 * @return 
	 */
	public Integer updateCourse(Course course);
}
