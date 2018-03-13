package com.example.demo.generator;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午3:32:04
 */
@Mapper
public interface SysGeneratorDao {

	/**
	 * 获得表信息：tableName, engine, tableComment, createTime
	 * @param tableName
	 * @return
	 */
	Map<String, String> queryTable(String tableName);

	/**
	 * 获得列信息：columnName, dataType, columnComment, columnKey, extra
	 * @param tableName
	 * @return
	 */
	List<Map<String, String>> queryColumns(String tableName);

}
