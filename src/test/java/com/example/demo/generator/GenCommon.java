package com.example.demo.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo.generator.GenConfig.*;

/**
 * @author dong
 * @date 2018/3/13
 */
public class GenCommon {
    //字段类型->java类型
    public static final Map<String, String> ATTR_JAVA_MAP = new HashMap<>();
    //代码模板数据
    public static final Map<String, String> PACKAGE_MAP = new HashMap<>();


    static {
        ATTR_JAVA_MAP.put("tinyint", "Integer");
        ATTR_JAVA_MAP.put("smallint", "Integer");
        ATTR_JAVA_MAP.put("mediumint", "Integer");
        ATTR_JAVA_MAP.put("int", "Integer");
        ATTR_JAVA_MAP.put("integer", "Integer");
        ATTR_JAVA_MAP.put("bigint", "Long");
        ATTR_JAVA_MAP.put("float", "Float");
        ATTR_JAVA_MAP.put("double", "Double");
        ATTR_JAVA_MAP.put("decimal", "BigDecimal");
        ATTR_JAVA_MAP.put("bit", "Boolean");
        ATTR_JAVA_MAP.put("char", "String");
        ATTR_JAVA_MAP.put("varchar", "String");
        ATTR_JAVA_MAP.put("tinytext", "String");
        ATTR_JAVA_MAP.put("text", "String");
        ATTR_JAVA_MAP.put("mediumtext", "String");
        ATTR_JAVA_MAP.put("longtext", "String");
        ATTR_JAVA_MAP.put("date", "Date");
        ATTR_JAVA_MAP.put("datetime", "Date");
        ATTR_JAVA_MAP.put("timestamp", "Date");

        PACKAGE_MAP.put("basePackage",BASE_PACKAGE);
        PACKAGE_MAP.put("modelPackage", PACKAGE_MODEL);
        PACKAGE_MAP.put("daoPackage", PACKAGE_DAO);
        PACKAGE_MAP.put("servicePackage", PACKAGE_SERVICE);
        PACKAGE_MAP.put("serviceImplPackage", PACKAGE_SERVICE_IMPL);
        PACKAGE_MAP.put("controllerPackage", PACKAGE_CONTROLLER);
        PACKAGE_MAP.put("mapperPackage", PACKAGE_MAPPER);
    }

    /**
     * 包名转成路径
     * @param packageName
     * @return
     */
    public static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    public enum EnumGenPath {
        ENTITY("Entity.java", PATH_MODEL),
        MAPPER("Dao.xml", PATH_MAPPER),
        DAO("Dao.java", PATH_DAO),
        SERVICE("Service.java", PATH_SERVICE),
        SERVICEIMPL("ServiceImpl.java", PATH_SERVICE_IMPL),
        CONTROLLER("Controller.java", PATH_CONTROLLER);

        EnumGenPath(String fileName, String placePath){
            this.fileName = fileName;
            this.placePath = placePath;
        }

        private String fileName;

        private String placePath;

        public String templatePath() {
            return TEMPLATE_FILE_PATH + fileName + ".vm";
        }

        public String genPath(String tableName){
            return placePath + CodeGenerator.nameToJava(tableName) + fileName;
        }

        public static String[] allGenPath(String tableName){
            List<String> list = new ArrayList<>();
            for (EnumGenPath genPath : EnumGenPath.values()){
                list.add(genPath.genPath(tableName));
            }
            String[] paths = new String[list.size()];
            list.toArray(paths);
            return paths;
        }
    }
}
