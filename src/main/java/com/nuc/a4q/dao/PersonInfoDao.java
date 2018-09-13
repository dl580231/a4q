package com.nuc.a4q.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

	/**
	 * 查询user信息列表（分页）
	 * 
	 * @param star
	 * @param end
	 * @return
	 */
	public List<PersonInfo> queryPersonInfoList(@Param("rowStart") Integer rowStart, @Param("rowSize") Integer rowSize,
			@Param("info") PersonInfo personInfo);

	/**
	 * 获取用户总数量
	 * 
	 * @return
	 */
	public Integer queryPersonInfoCount();
	
	/**
	 * 删除用户信息
	 * @param personInfo
	 * @return
	 */
	public Integer deleteUser(PersonInfo personInfo);
}
