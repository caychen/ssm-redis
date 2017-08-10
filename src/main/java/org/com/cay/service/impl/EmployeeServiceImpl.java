package org.com.cay.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.com.cay.dao.IEmployeeMapper;
import org.com.cay.entity.Employee;
import org.com.cay.redis.IJedisClient;
import org.com.cay.service.IEmployeeService;
import org.com.cay.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeMapper employeeMapper;
	
	@Autowired
	@Qualifier("jedisClientPool")
	private IJedisClient jedisClient;
	
	@Override
	public List<Employee> list() {
		// TODO Auto-generated method stub
		
		//先查询缓存
		//添加缓存不能影响正常业务逻辑
		try{
			String json = jedisClient.hget("emp", "list");
			if(StringUtils.isNotBlank(json)){
				List<Employee> employees = JsonUtils.jsonToList(json, Employee.class);
				return employees;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		List<Employee> list = employeeMapper.list();
		
		//把结果添加到缓存
		try {
			jedisClient.hset("emp", "list", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
