package com.nuc.a4q.web.floor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.a4q.entity.Floor;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Result;
import com.nuc.a4q.group.Delete;
import com.nuc.a4q.service.FloorService;
import com.nuc.a4q.utils.ResultUtil;

@Controller
@RequestMapping("floor")
public class FloorManagementController {
	@Autowired
	private FloorService service;
	
	/**
	 * 获得楼信息列表
	 * @param floor
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getFloorList",method=RequestMethod.POST)
	public Result getFloorList(Floor floor,PersonInfo user) {
		if(user.getUserId() != null) {
			floor.setUser(user);
		}
		List<Floor> list = service.getFloorList(floor);
		return ResultUtil.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value="removeFloor",method=RequestMethod.GET)
	public Result removeFloor(@Validated(Delete.class)Floor floor,BindingResult result) {
		if(result.hasErrors()) {
			return ResultUtil.error(result.getFieldError().getDefaultMessage());
		}
		service.removeFloor(floor);
		return ResultUtil.success();
	}
}
