package com.ryan.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SwaggerConfig
 * @Author Ryan
 * @Date 2026/3/5 11:02
 * @Version 1.0.0
 * @Description Swagger配置类
 */
@Component
@Data
@EnableOpenApi
@EnableWebMvc
@Slf4j
public class SwaggerConfig {

    /**
     * 创建用户模块接口文档
     */
    @Bean
    public Docket UserApiDocket() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("用户微服务接口文档")
                .pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ryan"))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .globalRequestParameters(globalRequestParameters()) // 新版 swagger 3.0 配置
                .globalResponses(HttpMethod.GET, globalResponses())
                .globalResponses(HttpMethod.POST, globalResponses());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Ryan商城实战项目")
                .description("用户微服务接口文档")
                .contact(new Contact("Ryan", "https://github.com/leslieKung", "lesliekanghao@gmail.com"))
                .version("1.0")
                .build();
    }

    /**
     * 初始化后台 AdminApiDoc 信息
     */
    @Bean
    public Docket AdminApiDoc() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("后台接口文档")
                .pathMapping("/")
                .enable(true) // 是否开启 swagger，false 为关闭，线上可以直接关闭
                .apiInfo(apiInfo()) // 配置 api 文档信息
                .select() // 选择哪些接口作为 swagger 的 doc 发布
                .apis(RequestHandlerSelectors.basePackage("com.ryan"))
                .paths(PathSelectors.ant("/admin/**")) // 正则匹配请求路径，并分配至当前组
                .build();
    }

    /**
     * 生成全局请求头参数
     * @return
     */
    private List<RequestParameter> globalRequestParameters() {
        List<RequestParameter> parameterList = new ArrayList<>();
        // 如需更多 直接复制修改即可
        parameterList.add(new RequestParameterBuilder()
                .name("token")
                .description("登录令牌信息")
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameterList;
    }

    private List<Response> globalResponses() {
        List<Response> respList = new ArrayList<>();
        respList.add(new ResponseBuilder().code("4xx").description("请求错误，请认真检查").build());
        return respList;
    }

    /**
     * 解决 SpringBoot 2.6 和 Swagger 2 3.0.0 冲突问题
     * @return
     */
    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }
            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .toList();
                mappings.clear();
                mappings.addAll(copy);
            }
            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    log.warn("修改WebMvcRequestHandlerProvider的属性：handlerMappings出错，可能导致swagger不可用", e);
                    throw new IllegalStateException(e);
                }
            }
        };
    }
}
