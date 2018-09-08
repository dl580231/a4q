package com.nuc.a4q.dao;

import com.nuc.a4q.entity.Course;

public interface CourseDao {
	public Integer insertCourse(Course course);
	public Integer deleteCourse(Course course);
	public Integer updateCourse(Course course);
}
