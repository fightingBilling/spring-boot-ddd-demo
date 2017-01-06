package ddd.demo.application.order;


import java.util.Date;

public class WarningOrderDto {
    private long orderId;
    private int[] waringType;
    private Date waringTime;
    private long vendorId;
    private int orderMark;
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
}
