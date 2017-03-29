package com.resttest.config;

import com.resttest.service.CurrentUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

/**
 * Created by kvasa on 01.01.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.resttest")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    private static final String[] CLASSPATH_RESOURCE_LOCATION = {"classpath:/view", "classpath:/static", "classpath:/view/admin-pages-panel"};

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("/main-panel/index");
        registry.addViewController("/taskservice").setViewName("/main-panel/task");
        registry.addViewController("/userservice/**").setViewName("/admin-panel/user");
        registry.addViewController("/userservice/changeuser/**").setViewName("/admin-panel/user");
        registry.addViewController("/userservice/profile").setViewName("/main-panel/account");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/testservice/teststable/**").setViewName("/admin-panel/tests");
        registry.addViewController("/testservice/questiontable/**").setViewName("/admin-panel/questions");
        registry.addViewController("/testservice/answertable/**").setViewName("/admin-panel/question");
        registry.addViewController("/userservice/usertable").setViewName("/admin-panel/table");
        registry.addViewController("/taskservice/manage").setViewName("/admin-panel/testmanage");
        registry.addViewController("/taskservice/entity/**").setViewName("/admin-panel/taskmanage");
        registry.addViewController("/test-processing").setViewName("/main-panel/test-passage");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATION);
        }
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("kvasar739@gmail.com");
        mailSender.setPassword("honornew1");
        mailSender.setDefaultEncoding("UTF-8");
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
        javaMailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CurrentUserDetailsService();
    }



}
