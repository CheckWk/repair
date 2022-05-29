package com.work.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;

/**
 * mybais-plus代码自动生成器
 * 根据数据库中表来生成实体类等代码
 *
 * @author jMz
 * @date 2022/1/12 0012
 * @description
 */
public class MyGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                //数据源配置，url需要修改
                new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/springboot_repair?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai","root","root")
                        .dbQuery(new MySqlQuery())
                        .schema("cloud_user")
                        .typeConvert(new MySqlTypeConvert())
                        .keyWordsHandler(new MySqlKeyWordsHandler())
        )
 
                //全局配置
                .globalConfig(builder -> {
                    builder.author("Wenkuo") // 设置作者
                            .disableOpenDir()//禁止打开输出目录
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir")+"/springboot_repair/src/main/java"); // 指定输出目录
                })
 
                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.work") // 设置父包名，根据实制项目路径修改
                            //.moduleName("blog")      // 父包名路径下再新建的文件夹
                            .entity("pojo.entity")         // 后面这些是sys文件夹里新建的各分类文件夹
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            //.other("other")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"/springboot_repair/src/main/resources/mapper")); // 存放mapper.xml路径
                })
 
                //策略配置
                .strategyConfig(builder -> {
                    builder
                            .addInclude(
//                                    "article_comment"
//                                    ,"article_comment_like_status"
//                                    ,"article_detail"
//                                    ,"article_tag"
//                                    ,"article_tag_referenced"
//                                    ,"article_user_favorite"
//                                    ,"sys_menu"
//                                    ,"sys_role",
//                                    "sys_role_menu"
//                                    ,"sys_user"
//                                    ,"sys_user_role"
                            ) // 设置需要生成的表名
                            //.addTablePrefix("tb_", "c_") // 设置过滤表前缀
                            .entityBuilder() //实体类配置
                            .enableLombok() //使用lombok
//                            .setRestControllerStyle(true);
                            .logicDeleteColumnName("del_flag")//逻辑删除字段名(数据库)
//                            .logicDeletePropertyName()
                            .enableTableFieldAnnotation()//实体类 字段注解
                            .controllerBuilder()//controller配置
                            .enableRestStyle()//开启restcontroller
                            .mapperBuilder()
                            .enableMapperAnnotation()//开启mapper注解
                            .enableBaseResultMap()//启用 BaseResultMap 生成
                            .enableBaseColumnList();//启用 BaseColumnList
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .templateConfig()
                .execute();
//        new TemplateConfig.Builder()
//                .disable(TemplateType.ENTITY)
//                .entity("/templates/entity.java")
//                .build();

    }
}