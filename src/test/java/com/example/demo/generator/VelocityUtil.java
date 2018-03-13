package com.example.demo.generator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class VelocityUtil {
    static {
        //设置velocity资源加载器
        //加载classpath目录
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
    }

    /**
     * 通过模板路径和数据得到生成内容
     * @param templatePath resource下的模板路径，如template/test.vm
     * @param dataMap 数据
     * @return
     */
    public static String getContentByTemplateAndData(String templatePath, Map<String, Object> dataMap) {
        //封装模板数据
        VelocityContext context = new VelocityContext(dataMap);
        //渲染模板获取内容
        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate(templatePath, "UTF-8");
        tpl.merge(context, sw);
        return sw.toString();
    }

    public static void main(String[] args) {
        String template = "generator/template/test.vm";
        Map<String, Object> map = new HashMap<>();
        map.put("test", "测试数据");
        String text = getContentByTemplateAndData(template, map);
        System.out.println(text);
    }
}
