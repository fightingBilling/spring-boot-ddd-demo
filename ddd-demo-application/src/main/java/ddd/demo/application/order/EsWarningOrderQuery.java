package ddd.demo.application.order;


public class EsWarningOrderQuery {
    private long orderId;
    private int warginType;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getWarginType() {
        return warginType;
    }

    public void setWarginType(int warginType) {
        this.warginType = warginType;
    }
}
