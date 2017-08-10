package org.com.cay.service.impl;

import java.util.List;

import org.com.cay.dao.IUserMapper;
import org.com.cay.entity.User;
import org.com.cay.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserMapper userMapper;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword("111111");
		userMapper.saveUser(user);

	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		List<User> list = userMapper.list();
		return list;
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		User user = userMapper.getUser(id);

		return user;
	}

}
