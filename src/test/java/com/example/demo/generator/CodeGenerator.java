package com.example.demo.generator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGenerator {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("generator/gen-mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static void main(String[] args) {
        genCode("sys_log");
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     * @param tableNames 数据表名称...
     */
    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {

            Map<String, Object> dataMap = getDataMap(tableName);

            generator(dataMap, GenCommon.EnumGenPath.ENTITY.templatePath(), GenCommon.EnumGenPath.ENTITY.genPath(tableName));
            generator(dataMap, GenCommon.EnumGenPath.DAO.templatePath(), GenCommon.EnumGenPath.DAO.genPath(tableName));
            generator(dataMap, GenCommon.EnumGenPath.MAPPER.templatePath(), GenCommon.EnumGenPath.MAPPER.genPath(tableName));
            generator(dataMap, GenCommon.EnumGenPath.SERVICE.templatePath(), GenCommon.EnumGenPath.SERVICE.genPath(tableName));
            generator(dataMap, GenCommon.EnumGenPath.SERVICEIMPL.templatePath(), GenCommon.EnumGenPath.SERVICEIMPL.genPath(tableName));
            generator(dataMap, GenCommon.EnumGenPath.CONTROLLER.templatePath(), GenCommon.EnumGenPath.CONTROLLER.genPath(tableName));
        }
    }

    private static void generator(Map<String, Object> dataMap, String templatePath, String genPath) {
        String code = VelocityUtil.getContentByTemplateAndData(templatePath, dataMap);
        writeFile(genPath, code);
    }

    /**
     * 模板数据：
     * @param tableName
     * @return
     */
    private static Map<String,Object> getDataMap(String tableName) {
        Map<String, Object> dataMap = new HashMap<>();
        //表信息
        Map<String, String> table = queryTable(tableName);
        String className = nameToJava(table.get("tableName"));
        table.put("className", className);
        table.put("classname", StringUtils.uncapitalize(className));

        //列信息
        List<Map<String, String>> columns = queryColumns(tableName);
        for (Map<String, String> column : columns) {
            String attrName = nameToJava(column.get("columnName"));
            column.put("attrName",attrName);
            column.put("attrname",StringUtils.uncapitalize(attrName));
            column.put("attrType",dataTypeConvertToAttrType(column.get("dataType")));
            if("PRI".equalsIgnoreCase(column.get("columnKey"))){
                dataMap.put("pk",column);
            }
        }

        //其它信息
        dataMap.put("package", GenCommon.PACKAGE_MAP);
        dataMap.put("author", GenConfig.AUTHOR);
        dataMap.put("datetime", GenConfig.DATE);

        for (Map.Entry<String, String> entry : table.entrySet()){
            dataMap.put(entry.getKey(), entry.getValue());
        }
//        dataMap.put("table",table);
        dataMap.put("columns",columns);

        return dataMap;
    }

    private static List<Map<String,String>> queryColumns(String tableName) {
        SqlSession session = sqlSessionFactory.openSession();
        SysGeneratorDao dao = session.getMapper(SysGeneratorDao.class);
        List<Map<String,String>> list = dao.queryColumns(tableName);
        session.close();
        return list;
    }

    private static Map<String,String> queryTable(String tableName) {
        SqlSession session = sqlSessionFactory.openSession();
        SysGeneratorDao dao = session.getMapper(SysGeneratorDao.class);
        Map<String,String> map = dao.queryTable(tableName);
        session.close();
        return map;
    }

    private static String dataTypeConvertToAttrType(String dataType) {
        if (GenCommon.ATTR_JAVA_MAP.get(dataType) == null){
            return "unknowType";
        }
        return GenCommon.ATTR_JAVA_MAP.get(dataType);
    }

    /**
     * 表名转换成Java类名
     */
    public static String nameToJava(String name) {
        return WordUtils.capitalizeFully(name, new char[]{'_'}).replace("_", "");
    }

    private static void writeFile(String pathName, String context){
        File file1 = new File(pathName);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        FileWriter fileWriter;
        try{
            fileWriter = new FileWriter(file1);
            //向指定文件中写入内容
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(context.toString());
            bufferedWriter.close();
            System.out.println("生成"+pathName+"成功");
        }catch(IOException e){
            System.out.println("生成"+pathName+"失败");
            e.printStackTrace();
        }
    }

}
