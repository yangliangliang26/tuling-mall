package com.tulingxueyuan.mall.common.config;

import com.tulingxueyuan.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger基础配置
 * Created by macro on 2020/7/16.
 */
public abstract class BaseSwaggerConfig {

    @Bean
    public Docket createRestApi() {
        SwaggerProperties swaggerProperties = swaggerProperties();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(swaggerProperties))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getApiBasePackage()))
                .paths(PathSelectors.any())
                .build();
        if (swaggerProperties.isEnableSecurity()) {
            docket.securitySchemes(securitySchemes()).securityContexts(securityContexts());
        }
        return docket;
    }

    private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }

    /**
     * 设置完成后进入SwaggerUI，出现“Authorization”按钮，点击即可输入我们配置的认证参数。
     * 对于不需要输入参数的接口（上文所述的包含auth的接口），在未输入Authorization参数就可以访问。
     * 通过Swagger2的securitySchemes配置全局参数：如下列代码所示，securitySchemes的ApiKey中增加一个名为“Authorization”，type为“header”的参数。
     * @return
     */
    private List<ApiKey> securitySchemes() {
        //设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        result.add(apiKey);
        return result;
    }
    /**
     * 在Swagger2的securityContexts中通过正则表达式，设置需要使用参数的接口（或者说，是去除掉不需要使用认证参数的接口），
     * 如下列代码所示，通过PathSelectors.regex("^(?!auth).*$")，所有包含"auth"的接口不需要使用securitySchemes。
     * 即不需要使用上文中设置的名为“Authorization”，type为“header”的认证参数。
     * 通俗讲，就是能匹配上的就使用默认认证，就不使用header里面的Authorization认证参数
     */
    private List<SecurityContext> securityContexts() {
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/*/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }

    /**
     * 自定义Swagger配置
     */
    public abstract SwaggerProperties swaggerProperties();
}
