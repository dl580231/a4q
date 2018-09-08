package com.nuc.a4q.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.PersonInfo;

public class CourseDaoTest extends BaseTest {
	@Autowired
	private CourseDao dao;
	
	@Test
	public void insertCourseTest() {
		Course course = new Course();
		PersonInfo per = new PersonInfo();
		course.setCourseName("java");
		per.setUserId(1);
		System.out.println(per);
		course.setModerator(per);
		course.setCreateTime(new Date());
		course.setLastEditTime(new Date());
		Integer integer = dao.insertCourse(course);
		System.out.println(integer);
	}
}
