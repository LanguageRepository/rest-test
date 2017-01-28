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
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/tasks").setViewName("tasks");
        registry.addViewController("/cpanel/**").setViewName("/admin-panel/user");
        registry.addViewController("/changeuser/**").setViewName("user");
        registry.addViewController("/profile").setViewName("profile");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/tests/**").setViewName("/admin-panel/tests");
        registry.addViewController("/table").setViewName("/admin-panel/table");
        registry.addViewController("/someservice").setViewName("/admin-panel/icons");
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
