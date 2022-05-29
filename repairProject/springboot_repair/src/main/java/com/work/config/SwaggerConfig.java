package com.work.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置类
 */
@EnableSwagger2
@Configuration
//Profile是方法1（只在dev和test环境下开启）
@Profile({"dev","test"})
public class SwaggerConfig implements WebMvcConfigurer {
    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    //这个是方法2哦，使用的话在new Docket里添加.Enable方法将参数放入即可
    @Value(value = "${swagger.show}")
    private Boolean swaggerEnabled;

    /**
    * apiInfo() 增加API相关信息
    * 所有的注解
    * .apis(RequestHandlerSelectors.any()) 
    * 指定部分注解1.Api.class(@APi),2.ApiOperation.class(@ApiOperation),3.ApiImplicitParam.class(@ApiImplicitParam)
    *.apis(RequestHandlerSelectors.withMethodAnnotation(Api.class))
    * 指定包路径
    * .apis(RequestHandlerSelectors.basePackage("这里填写需要的路径"))
    * .paths() 这个是包路径下的路径,PathSelectors.any()是包下所有路径
    */
    @Bean
    public Docket createRestApi() {
//        log.info(""+swaggerEnabled);
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.work.controller"))
                .paths(PathSelectors.any())
                //创建
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("物业报修管理系统接口")
                .description("此系统主要使用了SpringBoot、SpringSecurity、Redis等技术栈")
                // 作者信息
                .contact(new Contact("Wenkuo", "https://www.baidu.com", "abc@qq.com"))
                .version("0.0.1")
                .build();
    }

    //这个是可要可不要的，具体看需求
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}