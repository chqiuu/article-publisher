package com.chqiuu.publisher.config;

import cn.hutool.core.date.DatePattern;
import com.chqiuu.publisher.common.constant.Constant;
import com.chqiuu.publisher.config.converter.StringToIntegerConverter;
import com.chqiuu.publisher.config.converter.StringToLocalDateTimeConverter;
import com.chqiuu.publisher.config.converter.StringToLongConverter;
import com.chqiuu.publisher.config.converter.StringToMapConverter;
import com.chqiuu.publisher.config.interceptor.TimeConsumingInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.*;
import org.apache.hc.core5.http.URIScheme;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.nio.ssl.TlsStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.util.unit.DataSize;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ContentVersionStrategy;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public PublisherProperties properties() {
        return new PublisherProperties();
    }

    /**
     * 构造函数
     */
    public void setResourcePath() {
        ApplicationHome h = new ApplicationHome(getClass());
        String basePath = h.getSource().getParentFile().toString();
        String colon = ":";
        if (properties().getLocalResourcePath() == null) {
            // 没有配置则直接为Jar包路径
            Constant.ResourcePath.LOCAL_RESOURCE_PATH = basePath;
        } else if (properties().getLocalResourcePath().contains(colon)) {
            // 存在冒号为Windows绝对路径
            Constant.ResourcePath.LOCAL_RESOURCE_PATH = properties().getLocalResourcePath();
        } else if (properties().getLocalResourcePath().startsWith("/")) {
            // 存在冒号为Linux绝对路径
            Constant.ResourcePath.LOCAL_RESOURCE_PATH = properties().getLocalResourcePath();
        } else {
            // 将绝对路径存储到公用变量中
            Constant.ResourcePath.LOCAL_RESOURCE_PATH = basePath + "/" + properties().getLocalResourcePath();
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        setResourcePath();
        // 自定义文件静态资源目录为本机其他位置，也可以为JAR包的相对路径
        String resourcePath = String.format("file:///%s/resource/", Constant.ResourcePath.LOCAL_RESOURCE_PATH);
        // 设置静态资源
        registry.addResourceHandler("/favicon.ico").addResourceLocations(resourcePath, "classpath:/static/images/favicon.ico");
        registry.addResourceHandler("/static/**").addResourceLocations(resourcePath, "classpath:/static/");
        VersionResourceResolver versionResourceResolver = new VersionResourceResolver().addVersionStrategy(new ContentVersionStrategy(), "/**");
        registry.addResourceHandler("/resource/**").addResourceLocations(resourcePath)
                .setCachePeriod(2592000).resourceChain(true)
                .addResolver(versionResourceResolver);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设置首页地址 redirect forward
        registry.addViewController("/").setViewName("redirect:/static/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    /**
     * Controller 接收参数格式转换
     *
     * @param registry 格式器注册表
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateTimeConverter());
        registry.addConverter(new StringToIntegerConverter());
        registry.addConverter(new StringToLongConverter());
        registry.addConverter(new StringToMapConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeConsumingInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/swagger/**", "/static/**", "/admin/**", "/resource/**");
    }

    /**
     * 对返回前端的JSON数据进行格式化
     *
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        /*
        排除值为空属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        将驼峰转为下划线
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
         */
        // 进行缩进输出
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        // 解决延迟加载的对象
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 进行Date日期格式化
        DateFormat dateFormat = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        mapper.setDateFormat(dateFormat);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // 序列换成json时,将所有的long变成string ，处理Long类型转Json后精度丢失问题
        javaTimeModule.addSerializer(Long.class, ToStringSerializer.instance);
        javaTimeModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        // 进行LocalDateTime时间格式化
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        mapper.registerModule(javaTimeModule);
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        mapper.registerModule(new ParameterNamesModule()).registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());

        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // 支持接收List
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 根据属性名称排序
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        mapper.configure(MapperFeature.ALLOW_COERCION_OF_SCALARS, true);
        mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        return mapper;
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {

        // 1. 创建信任所有证书的 SSLContext
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(TrustAllStrategy.INSTANCE) // 关键：信任所有
                .build();

        // 2. 创建禁用主机名验证的 SSLConnectionSocketFactory
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
                sslContext,
                NoopHostnameVerifier.INSTANCE // 关键：禁用主机名验证
        );

        // 3. 创建连接管理器
        HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .build();

        // 4. 创建 HttpClient
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .evictExpiredConnections()
                .build();

        // 5. 创建 RequestFactory
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        // 6. 构建 RestTemplate
        return builder
                .requestFactory(() -> requestFactory)
                .build();
    }
}
