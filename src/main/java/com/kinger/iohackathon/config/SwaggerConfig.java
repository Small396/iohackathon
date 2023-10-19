package com.kinger.iohackathon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfig {
    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerDemoApiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.kinger.iohackathon.controller")) // 扫描的包
                .paths(PathSelectors.ant("/mock/**"))   // 过滤出指定的路径，在swagger-ui.html上进行展示
                .build();
    }
    private ApiInfo swaggerDemoApiInfo(){
        return new ApiInfoBuilder()
                .contact(new Contact("Lanh", "http://www.lanh.com", "xxx@163.com")) // 接触人，发送邮件
                //文档标题
                .title("这里是 Swagger 的标题")
                //文档描述
                .description("这里是 Swagger 的描述")
                //文档版本
                .version("1.0.0")
                .build();
    }

}
