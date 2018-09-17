package com.nuc.a4q.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.a4q.dao.FloorDao;
import com.nuc.a4q.entity.Floor;

@Service
public class FloorService {
	@Autowired
	private FloorDao dao;
	
	public List<Floor> getFloorList(Floor floor) {
		List<Floor> list = dao.queryFloorList(floor);
		return list;
	}

	public void removeFloor(Floor floor) {
		dao.deleteFloor(floor);
	}

	public Integer getFloorCountByPostId(Integer postId) {
		return dao.getFloorCountByPostId(postId);
	}
}
