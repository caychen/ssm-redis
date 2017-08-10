package org.com.cay.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.com.cay.dao.IUserMapper;
import org.com.cay.entity.User;
import org.com.cay.redis.IJedisClient;
import org.com.cay.service.IUserService;
import org.com.cay.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserMapper userMapper;

	@Autowired
	@Qualifier("jedisClientPool")
	private IJedisClient jedisClient;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword("111111");
		userMapper.saveUser(user);

		// 一旦新添加了数据，需要删除redis缓存中的数据，
		try {
			jedisClient.hdel("user", "list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		try {
			String json = jedisClient.hget("user", "list");
			if (StringUtils.isNotBlank(json)) {
				List<User> users = JsonUtils.jsonToList(json, User.class);
				return users;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<User> list = userMapper.list();

		try {
			jedisClient.hset("user", "list", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		try {
			String json = jedisClient.hget("user", id + "");
			if (StringUtils.isNotBlank(json)) {
				User user = JsonUtils.jsonToPojo(json, User.class);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		User user = userMapper.getUser(id);

		try {
			jedisClient.hset("user", id + "", JsonUtils.objectToJson(user));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
