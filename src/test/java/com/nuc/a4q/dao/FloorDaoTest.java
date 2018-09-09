package com.nuc.a4q.dao;

import java.util.Date;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.Floor;
import com.nuc.a4q.entity.PersonInfo;

public class FloorDaoTest extends BaseTest {
	@Autowired
	private FloorDao dao;

	@Test
	@Ignore
	public void queryFloorListTest() {
		Floor floor = new Floor();
		floor.setFloorContent("d");
		List<Floor> floorList = dao.queryFloorList(floor);
		System.out.println(floorList);
	}

	@Test
	@Ignore
	public void insertFloor() {
		Floor floor = new Floor();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1);
		floor.setUser(personInfo);
		floor.setPostId(1);
		floor.setFloorContent("demodemo");
		floor.setCreateTime(new Date());
		floor.setLastEditTime(new Date());
		Integer insertFloor = dao.insertFloor(floor);
		System.out.println(insertFloor);
	}
	
	@Test
	/*@Ignore*/
	public void deleteFloor() {
		Floor floor = new Floor();
		floor.setFloorContent("remove");
		Integer ressult = dao.deleteFloor(floor);
		System.out.println(ressult);
	}
}
