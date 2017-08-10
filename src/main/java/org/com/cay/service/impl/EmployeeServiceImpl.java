package org.com.cay.service.impl;

import java.util.List;

import org.com.cay.dao.IEmployeeMapper;
import org.com.cay.entity.Employee;
import org.com.cay.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeMapper employeeMapper;

	@Override
	public List<Employee> list() {
		// TODO Auto-generated method stub
		List<Employee> list = employeeMapper.list();

		return list;
	}

}
