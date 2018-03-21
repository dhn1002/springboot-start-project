package com.example.demo.module.service;

import com.example.demo.module.model.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author CodeGenerator
 * @date 2018/03/21
 */
public interface UserService {
	
	UserEntity queryObject(Integer id);
	
	List<UserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserEntity user);
	
	void update(UserEntity user);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
