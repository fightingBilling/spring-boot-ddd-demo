package ddd.demo.domain.order.model;

/**
 * 收货信息
 */
public class DeliveryAddressInfo {
    /**
     * 收货人姓名
     */
    private String name;
    /**
     * 收货人电话
     */
    private String phone;
    /**
     * 收货人详细地址
     */
    private String address;

    public DeliveryAddressInfo(String name, String phone, String address) {
        this.setName(name);
        this.setPhone(phone);
        this.setAddress(address);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }
}
