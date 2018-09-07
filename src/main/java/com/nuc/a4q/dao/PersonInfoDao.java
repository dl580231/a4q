package com.nuc.a4q.dao;

import com.nuc.a4q.entity.PersonInfo;

public interface PersonInfoDao {
	/**
	 * 增加用户信息
	 * 
	 * @param personInfo
	 * @return
	 */
	public Integer insertPersonInfo(PersonInfo personInfo);

	/**
	 * 根据userId查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public PersonInfo queryPresonInfo(PersonInfo personInfo);
}
