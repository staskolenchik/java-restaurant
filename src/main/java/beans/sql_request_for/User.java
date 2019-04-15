package beans.sql_request_for;

import beans.Dish;
import beans.Order;

public class User {
    private Dish dish;
    private Order order;

    public User(Dish dish, Order order) {
        this.dish = dish;
        this.order = order;
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
