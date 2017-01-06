package ddd.demo.infrastructure.elasticsearch;


import ddd.demo.application.order.IOrderElasticSearchQuery;
import ddd.demo.infrastructure.elasticsearch.order.OrderElasticSearchQuery;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;

@Configuration
@Profile("default")
public class ElasticsSearchConfig {
    @Bean
    public Client transportClient() throws Exception {
        return TransportClient.builder().build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(transportClient());
    }

    @Bean
    public IOrderElasticSearchQuery orderElasticSearchQuery() throws Exception {
        return new OrderElasticSearchQuery(elasticsearchTemplate());
    }



}
