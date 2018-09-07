package com.nuc.a4q.service;

import com.nuc.a4q.dto.PersonInfoDto;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.exception.PersonInfoOperationException;

public interface PersonInfoService {
	/**
	 * 注册用户信息
	 * 
	 * @param personInfo
	 * @return
	 * @throws PersonInfoOperationException
	 */
	public PersonInfoDto addPersoninfo(PersonInfo personInfo) throws PersonInfoOperationException;

	/**
	 * 查询用户信息
	 * 
	 * @param personInfo
	 * @return
	 * @throws PersonInfoOperationException
	 */
	public PersonInfoDto loginAuth(PersonInfo personInfo) throws PersonInfoOperationException;
}
