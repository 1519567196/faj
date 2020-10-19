package com.resjz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@MapperScan(basePackages = {"com.resjz.common.dao.**.dao"})
public class ApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
//		final String APP_KEY = "c1ebc6fad8466bd6";
//		final String ANALYSYS_SERVICE_URL = "https://arkcloud-0813.analysys.cn:4089/";
//		AnalysysJavaSdk analysys = new AnalysysJavaSdk(new SyncCollecter(ANALYSYS_SERVICE_URL), APP_KEY);
		SpringApplication.run(ApiApplication.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(ApiApplication.class);
	}
}
