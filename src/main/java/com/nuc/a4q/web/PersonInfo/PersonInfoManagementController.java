package com.nuc.a4q.web.PersonInfo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Result;
import com.nuc.a4q.enums.ResultEnum;
import com.nuc.a4q.service.PersonInfoService;
import com.nuc.a4q.utils.ResultUtil;

@Controller
@RequestMapping("personInfoAdmin")
public class PersonInfoManagementController {
	@Autowired
	private PersonInfoService service;

	@RequestMapping(value = "userRegister", method = RequestMethod.POST)
	@ResponseBody
	public Result userRegister(@RequestBody @Validated PersonInfo personInfo, BindingResult bindingResult) {
		// 0.表单验证
		if (bindingResult.hasErrors()) {
			return ResultUtil.error(ResultEnum.FORM_AUTH_ERROR.getState(),
					bindingResult.getFieldError().getDefaultMessage());
		}
		// 1.接受参数
		// 2.调用service层
		service.addPersoninfo(personInfo);
		// 3.返回结果
		return ResultUtil.success();
	}

	@ResponseBody
	@RequestMapping(value = "loginAuth", method = RequestMethod.POST)
	public Result loginAuth(@RequestBody PersonInfo personInfo, HttpServletRequest request) {
		// 1.接受参数
		// 2.调用service层
		PersonInfo user = service.loginAuth(personInfo);
		request.getSession().setAttribute("user", user);
		// 3.返回结果
		return ResultUtil.success();
	}

}
