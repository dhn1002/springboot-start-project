package com.example.demo.generator;

import static com.example.demo.generator.CodeGenerator.genCode;

/**
 * @author dong
 * @date 2018/3/21
 */
public class GenApplication {
    public static final String[] TABLES = {"user"};

    public static void main(String[] args) {
        genCode(TABLES);
//        deleteGenFile(TABLES);
    }
}
