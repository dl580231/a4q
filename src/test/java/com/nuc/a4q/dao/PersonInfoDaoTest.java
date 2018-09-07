package com.nuc.a4q.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.PersonInfo;

public class PersonInfoDaoTest extends BaseTest{
	@Autowired
	private PersonInfoDao dao;
	
	@Test
	public void insertPersonInfoTest() {
		PersonInfo info = new PersonInfo();
		info.setUserName("demo");
		info.setGender("男");
		info.setEmail("657838012@qq.com");
		info.setPhone("18235105722");
		info.setUserType(0);
		info.setCreateTime(new Date());
		info.setLastEditTime(new Date());
		Integer i = dao.insertPersonInfo(info);
		System.out.println(i);
	}
}
