package hello.app.model;

public class Order {

    private long orderId;
    private String userName;

    public Order() {

    }

    public Order(long orderId, String userName) {
        this.orderId = orderId;
        this.userName = userName;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
