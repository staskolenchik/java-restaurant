package beans;

public class Order {
    private int orderId;
    private String orderUserName;
    private String orderDishName;
    private byte orderQuantity;
    private String orderDate;
    private double orderTotalCost;

    public Order() {
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
}
