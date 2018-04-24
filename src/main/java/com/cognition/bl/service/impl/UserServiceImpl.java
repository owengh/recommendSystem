package com.cognition.bl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cognition.bl.dao.UserMapper;
import com.cognition.bl.domain.SalesOrder;
import com.cognition.bl.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	
	@Autowired
	UserMapper userMapper;
	
	
	@Override
	public SalesOrder findById(String orderNO) {
		return userMapper.findById(orderNO);
	}

}
