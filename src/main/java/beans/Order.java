package beans;

public class Order {
    private int orderId;
    private byte orderQuantity;
    private String orderDate;
    private int orderUserId;
    private int orderDishId;
    private String orderStatus;

    public Order() {
    }

    public Order(byte orderQuantity, int orderUserId, int orderDishId) {
        this.orderQuantity = orderQuantity;
        this.orderUserId = orderUserId;
        this.orderDishId = orderDishId;
    }

    public Order(int orderId, byte orderQuantity, String orderDate, double orderTotalCost) {
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
    }

    public Order(int orderId, String orderDate, byte orderQuantity, String orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderQuantity = orderQuantity;
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public byte getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(byte orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(int orderUserId) {
        this.orderUserId = orderUserId;
    }

    public int getOrderDishId() {
        return orderDishId;
    }

    public void setOrderDishId(int orderDishId) {
        this.orderDishId = orderDishId;
    }
}
