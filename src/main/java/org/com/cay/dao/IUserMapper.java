package org.com.cay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.com.cay.entity.User;

public interface IUserMapper {

	public void saveUser(User user);
	
	public User getUser(@Param("id")Integer id);

	public List<User> list();
}
