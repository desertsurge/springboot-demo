package com.liutao.demo.config;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class AppConfig {

	@Bean
	public HttpMessageConverters fastjsonHttpMessageConverter() {
		System.out.println("===============");
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteNullStringAsEmpty);
		converter.setFastJsonConfig(fastJsonConfig);
		return new HttpMessageConverters(converter);
	}
}
