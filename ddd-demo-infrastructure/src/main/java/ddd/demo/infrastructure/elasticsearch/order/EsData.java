package ddd.demo.infrastructure.elasticsearch.order;


import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "w1", type = "type1")
public class EsData {

    @Field(type = FieldType.String,index = FieldIndex.not_analyzed,store = true)
    private String id;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String name;

    @Field(type = FieldType.Long,index = FieldIndex.not_analyzed,store = true)
    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
