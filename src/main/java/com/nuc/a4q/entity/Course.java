package com.nuc.a4q.entity;
/**
 * 
 * @author DL
 *
 *CREATE TABLE `tb_course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `moderator_id` int(11) NOT NULL,
  `course_name` varchar(255) NOT NULL,
  `priority` int(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `moderator_id` (`moderator_id`),
  CONSTRAINT `tb_course_ibfk_1` FOREIGN KEY (`moderator_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


 */

import java.util.Date;

public class Course {
	private Integer courseId;
	private PersonInfo moderator;// 版主
	private String courseName;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public PersonInfo getModerator() {
		return moderator;
	}

	public void setModerator(PersonInfo moderator) {
		this.moderator = moderator;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", moderator=" + moderator + ", courseName=" + courseName
				+ ", priority=" + priority + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}

}
