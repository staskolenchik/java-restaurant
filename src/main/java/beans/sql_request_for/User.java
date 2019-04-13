package beans.sql_request_for;

import beans.Dish;
import beans.Order;

public class User {
    private Dish dish;
    private Order order;

    public User(int orderId, String dishName, String dishDescription, String dishType,
                   double dishPrice, byte orderQuantity, String orderStatus, double orderTotalCost) {
        this.dish = new Dish(dishName, dishDescription, dishType, dishPrice);
        this.order = new Order(orderId, orderQuantity, orderTotalCost, orderStatus);
    }

    public int getOrderId() {
        return order.getOrderId();
    }

    public String getDishName() {
        return dish.getName();
    }

    public String getDishDescription() {
        return dish.getDescription();
    }

    public String getDishType() {
        return dish.getDishType();
    }

    public double getDishPrice() {
        return dish.getDishPrice();
    }

    public byte getOrderQuantity() {
        return order.getOrderQuantity();
    }

    public String getOrderStatus() {
        return order.getOrderStatus();
    }

    public double getOrderTotalCost() {
        return order.getOrderTotalCost();
    }
}
