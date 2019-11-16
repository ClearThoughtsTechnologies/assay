package com.clearthoughts.clearcampus.assay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
public class AssayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssayApplication.class, args);
	}
/*
	@Autowired
	PluginRegistryFactoryBean<LinkRelationProvider, LookupContext> pluginRegistryFactoryBean;

	@Bean
	@Primary
	public OrderAwarePluginRegistry discoverers() {
		return pluginRegistryFactoryBean.getObject();
	}
*/

	
}
