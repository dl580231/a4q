package com.nuc.a4q.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuc.a4q.dao.PersonInfoDao;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.enums.ResultEnum;
import com.nuc.a4q.exception.LogicException;

@Service
@Transactional
public class PersonInfoService {
	@Autowired
	private PersonInfoDao dao;

	/**
	 * 用户信息注册
	 * 
	 * @param personInfo
	 * @throws Exception
	 */
	public void addPersoninfo(PersonInfo personInfo) {
		// 1.判断参数是否为空
		// 2.补充一些信息
		personInfo.setCreateTime(new Date());
		personInfo.setLastEditTime(new Date());
		// 3.操作数据库
		duplicateJudge(personInfo);// 字段重复判断
		dao.insertPersonInfo(personInfo);
	}

	/**
	 * 登陆认证
	 * 
	 * @param personInfo
	 * @return
	 * @throws Exception
	 */
	public PersonInfo loginAuth(PersonInfo personInfo) {
		if (personInfo == null || personInfo.getPassword() == null
				|| (personInfo.getPhone() == null && personInfo.getEmail() == null)) {
			throw new LogicException("用户名或密码不能为空");
		}
		PersonInfo info = dao.queryPresonInfo(personInfo);
		if (info == null) {
			throw new LogicException("用户名不存在");
		} else {
			if (personInfo.getPassword().equals(info.getPassword())) {
				personInfo.setPassword(null);
				return personInfo;
			} else {
				throw new LogicException("用户名或密码错误");
			}
		}
	}

	/**
	 * 验证数据库中的email和phone是否重复
	 * 
	 * @param personInfo
	 */
	public void duplicateJudge(PersonInfo personInfo) {
		PersonInfo info = new PersonInfo();
		info.setEmail(personInfo.getEmail());
		PersonInfo resultEmail = dao.queryPresonInfo(info);
		if (resultEmail != null) {
			throw new LogicException(ResultEnum.DUPLICATE_EMAIL.getState(), ResultEnum.DUPLICATE_EMAIL.getStateInfo());
		}
		info.setEmail(null);
		info.setPhone(personInfo.getPhone());
		PersonInfo resultPhone = dao.queryPresonInfo(info);
		if (resultPhone != null) {
			throw new LogicException(ResultEnum.DUPLICATE_PHONE.getState(), ResultEnum.DUPLICATE_PHONE.getStateInfo());
		}
	}
}
