package com.it.groupware.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.it.groupware.commoncontroller.AdminPageInterceptor;
import com.it.groupware.commoncontroller.LoginInterceptor;
import com.it.groupware.commoncontroller.LoginToLoginInterceptor;


@Configuration
public class MvcConfiguration implements WebMvcConfigurer{

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		
//		/* 로그인 안하고 접근하는 경우*/
//		registry.addInterceptor(new LoginInterceptor())
//		.excludePathPatterns("/login/login","/login/findPwd")
//		.addPathPatterns("/*/*");
//		
//		/* 로그인상태에서 접근하는 경우*/
//		registry.addInterceptor(new LoginToLoginInterceptor())
//		.addPathPatterns("/");
//		
//		
//		/* 관리자 페이지 접근하는 경우*/
//		registry.addInterceptor(new AdminPageInterceptor())
//		.addPathPatterns("/admin","/assiduity/excess","/booking/property","/booking/rent",
//						"/adminBoard/main","/emp/empList","/emp/empWrite");
//		
//	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver 
			= new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8"); // 파일 인코딩 설정
		multipartResolver.setMaxUploadSizePerFile(50 * 1024 * 1024); // 파일당 업로드 크기 제한 (50MB)
		return multipartResolver;
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		default 설정.
//		Allow all origins.
//		Allow "simple" methods GET, HEAD and POST.
//		Allow all headers.
//		Set max age to 1800 seconds (30 minutes).
		registry.addMapping("/**")
			.allowedOrigins("*")
//			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//			.allowedHeaders("*")
			.maxAge(6000);
	}
	
}
