package com.example.demo.module.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.example.demo.module.dao.UserDao;
import com.example.demo.module.model.UserEntity;
import com.example.demo.module.service.UserService;



@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserEntity queryObject(Integer id){
		return userDao.queryObject(id);
	}
	
	@Override
	public List<UserEntity> queryList(Map<String, Object> map){
		return userDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userDao.queryTotal(map);
	}
	
	@Override
	public void save(UserEntity user){
		userDao.save(user);
	}
	
	@Override
	public void update(UserEntity user){
		userDao.update(user);
	}
	
	@Override
	public void delete(Integer id){
		userDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userDao.deleteBatch(ids);
	}
	
}
