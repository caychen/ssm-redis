package org.com.cay.service;

import java.util.List;

import org.com.cay.entity.User;

public interface IUserService {

	void saveUser(User user);

	List<User> list();

	User getUser(Integer id);

}
