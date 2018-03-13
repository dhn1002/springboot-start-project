package com.example.demo.generator;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.demo.generator.GenCommon.packageConvertPath;

/**
 * @author dong
 * @date 2018/3/9
 */
public class GenConfig {

    public static final String AUTHOR = "CodeGenerator";//@author
    public static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date

    //包信息，可修改
    public static final String BASE_PACKAGE = "com.example.demo";//项目基础包名称，根据自己公司的项目修改

    public static final String PACKAGE_MODEL = "com.example.demo.module.model";//Model所在包
    public static final String PACKAGE_DAO = "com.example.demo.module.dao";//dao所在包
    public static final String PACKAGE_SERVICE = "com.example.demo.module.service";//Service所在包
    public static final String PACKAGE_SERVICE_IMPL = "com.example.demo.module.service.impl";//ServiceImpl所在包
    public static final String PACKAGE_CONTROLLER = "com.example.demo.module.controller";//Controller所在包
    public static final String PACKAGE_MAPPER = "mapper";//Mapper所在包

    //基础路径，无需修改
    private static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    private static final String JAVA_PATH = "/src/main/java"; //java文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径
    public static final String TEMPLATE_FILE_PATH = "generator/template/";//相对于resource下的模板位置

    //根据包名获得存放路径，无需修改
    public static final String PATH_MODEL = PROJECT_PATH + JAVA_PATH + packageConvertPath(PACKAGE_MODEL);//生成的entity存放路径
    public static final String PATH_DAO = PROJECT_PATH + JAVA_PATH + packageConvertPath(PACKAGE_DAO);//生成的dao存放路径
    public static final String PATH_MAPPER = PROJECT_PATH + RESOURCES_PATH + packageConvertPath(PACKAGE_MAPPER);//生成的mapper存放路径
    public static final String PATH_SERVICE = PROJECT_PATH + JAVA_PATH + packageConvertPath(PACKAGE_SERVICE);//生成的Service存放路径
    public static final String PATH_SERVICE_IMPL = PROJECT_PATH + JAVA_PATH + packageConvertPath(PACKAGE_SERVICE_IMPL);//生成的Service实现存放路径
    public static final String PATH_CONTROLLER = PROJECT_PATH + JAVA_PATH + packageConvertPath(PACKAGE_CONTROLLER);//生成的Controller存放路径

}
