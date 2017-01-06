package ddd.demo.infrastructure.elasticsearch.order;

import ddd.demo.application.order.EsWarningOrderQuery;
import ddd.demo.application.order.IOrderElasticSearchQuery;
import ddd.demo.application.order.WarningOrderDto;
import ddd.demo.infrastructure.elasticsearch.ElasticsSearchConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static javafx.scene.input.KeyCode.T;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ElasticsSearchConfig.class, MockElasticsSearchConfig.class})
@ActiveProfiles("default")
public class OrderElasticSearchQueryTest {

    @Autowired
    IOrderElasticSearchQuery query;

    @Before
    public void init() {
        this.query.createWarningOrderDoc();
    }

    @Test
    public void findAllTest() {
        this.query.findAll();
    }


    @Test
    public void createMapping() {
        this.query.createMapping();
    }

    @Test
    public void UpdateStatusTest() {

        this.query.updatesStatus(102, 10, 2);

        EsWarningOrderQuery esWarningOrderQuery = new EsWarningOrderQuery();
        esWarningOrderQuery.setOrderId(102);

        List<WarningOrderDto> list = this.query.select(esWarningOrderQuery, 1, 1, 10);

        Assert.assertEquals(list.get(0).getStatus(), 2);
    }


    @Test
    public void selectTest() {
        List<WarningOrderDto> list = this.query.select(new EsWarningOrderQuery(), 1, 10, 10);

        Assert.assertTrue(list.size() > 0);

        EsWarningOrderQuery esWarningOrderQuery = new EsWarningOrderQuery();
        esWarningOrderQuery.setOrderId(100);

        list = this.query.select(esWarningOrderQuery, 1, 10, 10);

        Assert.assertTrue(list.size() == 1);
    }

    @After
    public void clear() {
        this.query.clear();
    }

}
