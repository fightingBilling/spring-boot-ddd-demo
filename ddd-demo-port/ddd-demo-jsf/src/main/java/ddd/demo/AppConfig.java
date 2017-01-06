package ddd.demo;

import ddd.demo.application.ApplicationConfig;
import ddd.demo.infrastructure.elasticsearch.ElasticsSearchConfig;
import ddd.demo.infrastructure.repository.RepositoryConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(value = {RepositoryConfig.class, ElasticsSearchConfig.class, ApplicationConfig.class})
@ImportResource("classpath:/spring-jsf.xml")
public class AppConfig {


}
