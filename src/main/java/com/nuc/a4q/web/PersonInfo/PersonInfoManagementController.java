package com.nuc.a4q.web.PersonInfo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.a4q.dto.PersonInfoDto;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.enums.PersonInfoStateEnum;
import com.nuc.a4q.exception.PersonInfoOperationException;
import com.nuc.a4q.service.PersonInfoService;

@Controller
@RequestMapping("personInfoAdmin")
public class PersonInfoManagementController {
	@Autowired
	private PersonInfoService service;

	@RequestMapping(value = "userRegister", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userRegister(@RequestBody PersonInfo personInfo) {
		Map<String, Object> model = new HashMap<>();
		// 1.接受参数
		// 2.调用servicec层
		try {
			PersonInfoDto dto = service.addPersoninfo(personInfo);
			if (dto.getEnum1().getState() == PersonInfoStateEnum.SUCCESS.getState()) {
				model.put("success", true);
			} else {
				model.put("success", false);
				model.put("errorMsg", dto.getEnum1().getStateInfo());
			}
		} catch (PersonInfoOperationException e) {
			model.put("success", false);
			model.put("errorMsg", "出现异常" + e.getMessage());
		}
		// 3.返回结果
		return model;
	}

	@RequestMapping(value = "loginAuth", method = RequestMethod.POST)
	public Map<String, Object> loginAuth(@RequestBody PersonInfo personInfo, HttpServletRequest request) {
		Map<String, Object> model = new HashMap<>();
		// 1.接受参数
		// 2.调用servicec层
		try {
			PersonInfoDto dto = service.loginAuth(personInfo);
			if (dto.getEnum1().getState() == PersonInfoStateEnum.SUCCESS.getState()) {
				model.put("success", true);
				request.getSession().setAttribute("user", dto.getPersonInfo());
			} else {
				model.put("success", false);
				model.put("errorMsg", dto.getEnum1().getStateInfo());
			}
		} catch (PersonInfoOperationException e) {
			model.put("success", false);
			model.put("errorMsg", e.getMessage());
		}
		// 3.返回结果
		return model;
	}
}
