package com.nuc.a4q.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.dto.PersonInfoDto;
import com.nuc.a4q.entity.PersonInfo;

public class PersonInfoServiceTest extends BaseTest {
	@Autowired
	PersonInfoService service;

	@Test
	/*@Ignore*/
	public void loginAuthTest() {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setPhone("18235105722");
		personInfo.setPassword("580231");
		PersonInfoDto dto = service.loginAuth(personInfo);
		System.out.println(dto.getEnum1().getStateInfo());
	}
	
	
}
