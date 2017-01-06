package ddd.demo.application.order;

import ddd.demo.application.ApplicationConfig;
import ddd.demo.infrastructure.elasticsearch.ElasticsSearchConfig;
import ddd.demo.infrastructure.repository.RepositoryConfig;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {ApplicationConfig.class, RepositoryConfig.class, ElasticsSearchConfig.class})
public class BaseTest {
}
