package beans;

public class Order {
    private int orderId;
    private String orderUserName;
    private String orderDishName;
    private byte orderQuantity;
    private String orderDate;
    private double orderTotalCost;
    private int orderUserId;
    private int orderDishId;
    private String orderStatus;

    public Order() {
    }

    public Order(byte orderQuantity, double orderTotalCost, int orderUserId, int orderDishId) {
        this.orderQuantity = orderQuantity;
        this.orderTotalCost = orderTotalCost;
        this.orderUserId = orderUserId;
        this.orderDishId = orderDishId;
    }

    public Order(int orderId, String orderUserName, String orderDishName,
                 byte orderQuantity, String orderDate, double orderTotalCost) {
        this.orderId = orderId;
        this.orderUserName = orderUserName;
        this.orderDishName = orderDishName;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.orderTotalCost = orderTotalCost;
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

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public String getOrderDishName() {
        return orderDishName;
    }

    public void setOrderDishName(String orderDishName) {
        this.orderDishName = orderDishName;
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

    public double getOrderTotalCost() {
        return orderTotalCost;
    }

    public void setOrderTotalCost(double orderTotalCost) {
        this.orderTotalCost = orderTotalCost;
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
