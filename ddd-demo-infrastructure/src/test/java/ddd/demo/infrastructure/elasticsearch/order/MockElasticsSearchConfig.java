package ddd.demo.infrastructure.elasticsearch.order;


import ddd.demo.application.order.IOrderElasticSearchQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value = "test")
public class MockElasticsSearchConfig {
    @Bean
    public IOrderElasticSearchQuery mockOrderElasticSearchQuery() throws Exception {
        return new MockOrderElasticSearchQuery();
    }
}
