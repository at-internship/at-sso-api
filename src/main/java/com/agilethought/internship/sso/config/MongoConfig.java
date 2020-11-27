package com.agilethought.internship.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MongoConfig {
	RestTemplate restTemplate;

	@Autowired
	private MongoDatabaseFactory mongoDatabaseFactory;

	@Autowired
	private MongoMappingContext mongoMappingContext;

	@Bean
	public MappingMongoConverter mappingMongoConverter() {
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		log.info("ControllerAplication.mappingMongoConverter - mappingMongoConverter was successfull", converter);
		return converter;
	}
}
