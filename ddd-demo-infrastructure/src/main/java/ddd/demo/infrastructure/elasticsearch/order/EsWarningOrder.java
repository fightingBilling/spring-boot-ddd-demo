package ddd.demo.infrastructure.elasticsearch.order;


import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "tjz", type = "EsWarningOrder")
public class EsWarningOrder {

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String id;

    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
    private long orderId;
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed)
    private int[] waringType;
    @Field(type = FieldType.Date, index = FieldIndex.not_analyzed)
    private Date waringTime;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
    private long vendorId;
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed)
    private int orderMark;
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed)
    private int status;


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int[] getWaringType() {
        return waringType;
    }

    public void setWaringType(int[] waringType) {
        this.waringType = waringType;
    }

    public Date getWaringTime() {
        return waringTime;
    }

    public void setWaringTime(Date waringTime) {
        this.waringTime = waringTime;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public int getOrderMark() {
        return orderMark;
    }

    public void setOrderMark(int orderMark) {
        this.orderMark = orderMark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
