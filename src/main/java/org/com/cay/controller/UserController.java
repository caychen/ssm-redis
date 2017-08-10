package org.com.cay.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.com.cay.entity.Employee;
import org.com.cay.entity.User;
import org.com.cay.service.IEmployeeService;
import org.com.cay.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping("/toadd")
	public ModelAndView toAddUser(){
		logger.info("user对象添加...");
		ModelAndView mv = new ModelAndView("add");
		List<Employee> employees = employeeService.list();
		mv.addObject("employees", employees);
		return mv;
	}
	
	@RequestMapping("/save")
	public String saveUser(User user){
		logger.info("user对象保存...");
		userService.saveUser(user);
		return "redirect:list";
	}
	
	@RequestMapping("/get/{id}")
	@ResponseBody
	public User getUser(@PathVariable("id") Integer id){
		logger.info("user对象查询...");
		return userService.getUser(id);
	}
	
	@RequestMapping("/list")
	public ModelAndView list(){
		logger.info("查询所有user数据...");
		ModelAndView mv = new ModelAndView("list");
		List<User> users = userService.list();
		mv.addObject("users", users);
		return mv;
	}
}
