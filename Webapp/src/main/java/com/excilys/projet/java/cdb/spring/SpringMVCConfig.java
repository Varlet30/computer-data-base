package com.excilys.projet.java.cdb.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.LocaleResolver;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class SpringMVCConfig implements WebMvcConfigurer{
		
	 @Bean
	 public ViewResolver getViewLocation() 
	 {
	 	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setPrefix("/WEB-INF/views/");
	    viewResolver.setSuffix(".jsp");
	      
	    return viewResolver;
	 }
	   
	 public void addResourceHandlers(ResourceHandlerRegistry registry) 
	 {
	    registry
	    	.addResourceHandler("/resources/**")
	        .addResourceLocations("/resources/"); 
	 }
	   
	 @Bean("messageSource")
	 public MessageSource messageSource() 
	 {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:locale/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		    
	 return messageSource;
	 }
		
	 @Bean
	 public LocaleResolver localeResolver() 
	 {
		 CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		 
		 return localeResolver;
	 }
		 
	 public void addInterceptors(InterceptorRegistry registry) 
	 {
		 ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor();
		 themeChangeInterceptor.setParamName("theme");
		 registry.addInterceptor(themeChangeInterceptor);
		    
		 LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		 localeChangeInterceptor.setParamName("lang");
		 registry.addInterceptor(localeChangeInterceptor);
	 }
}