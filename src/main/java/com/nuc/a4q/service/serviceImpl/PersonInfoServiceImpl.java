package com.nuc.a4q.service.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuc.a4q.dao.PersonInfoDao;
import com.nuc.a4q.dto.PersonInfoDto;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.enums.PersonInfoStateEnum;
import com.nuc.a4q.exception.PersonInfoOperationException;
import com.nuc.a4q.service.PersonInfoService;

@Service
@Transactional
public class PersonInfoServiceImpl implements PersonInfoService {
	@Autowired
	private PersonInfoDao dao;

	@Override
	public PersonInfoDto addPersoninfo(PersonInfo personInfo) throws PersonInfoOperationException {
		// 1.判断参数是否为空
		if (personInfo == null) {
			return new PersonInfoDto(PersonInfoStateEnum.NULL_INFO);
		} else {
			// 2.补充一些信息
			personInfo.setCreateTime(new Date());
			personInfo.setLastEditTime(new Date());
			// 3.操作数据库
			try {
				Integer result = dao.insertPersonInfo(personInfo);
				if (result < 1) {
					throw new Exception("注册失败");
				} else {
					return new PersonInfoDto(PersonInfoStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new PersonInfoOperationException("注册失败" + e.getMessage());
			}
		}
	}

	public PersonInfoDto loginAuth(PersonInfo personInfo) throws PersonInfoOperationException {
		if (personInfo == null || personInfo.getPassword() == null
				|| (personInfo.getPhone() == null && personInfo.getEmail() == null)) {
			return new PersonInfoDto(PersonInfoStateEnum.NULL_INFO);
		}
		try {
			PersonInfo info = dao.queryPresonInfo(personInfo);
			if (info == null) {
				return new PersonInfoDto(PersonInfoStateEnum.NOT_EXIST);
			} else {
				if (personInfo.getPassword().equals(info.getPassword())) {
					personInfo.setPassword(null);
					return new PersonInfoDto(PersonInfoStateEnum.SUCCESS,personInfo);
				} else {
					return new PersonInfoDto(PersonInfoStateEnum.AUTH_FILED);
				}
			}
		} catch (Exception e) {
			throw new PersonInfoOperationException("验证失败" + e.getMessage());
		}
	}

}
