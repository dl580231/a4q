package com.nuc.a4q.entity;

import java.util.Date;

/**
 * 
 * @author DL
 *
 *         CREATE TABLE `tb_person_info` ( `user_id` int(11) NOT NULL
 *         AUTO_INCREMENT, `user_name` varchar(255) NOT NULL, `gender`
 *         varchar(2) DEFAULT NULL, `profile_img` varchar(1024) DEFAULT NULL,
 *         `phone` varchar(20) DEFAULT NULL, `email` varchar(20) DEFAULT NULL,
 *         `user_type` int(2) NOT NULL DEFAULT '0' COMMENT '0：学生；1.老师', `lable`
 *         varchar(255) DEFAULT NULL COMMENT '个人标签', `create_time` datetime
 *         DEFAULT NULL, `last_edit_time` datetime DEFAULT NULL, PRIMARY KEY
 *         (`user_id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * 
 * 
 * 
 * 
 */
public class PersonInfo {
	private Integer userId;
	private String userName;// 用户的名字
	private String gender;// 用户性别
	private String profileImg;// 用户头像地址
	private String phone;
	private String email;
	private String password;
	/**
	 * 用户身份 0.学生 1.老师，默认为0
	 */
	private Integer userType;
	private String lable;// 用户标签
	private Date createTime;
	private Date lastEditTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "PersonInfo [userId=" + userId + ", userName=" + userName + ", gender=" + gender + ", profileImg="
				+ profileImg + ", phone=" + phone + ", email=" + email + ", password=" + password + ", userType="
				+ userType + ", lable=" + lable + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}

}
