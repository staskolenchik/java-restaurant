package beans.sql_request_for;

import beans.Dish;
import beans.Order;

public class Kitchen {

    private Dish dish;
    private Order order;

    public Kitchen(Dish dish, Order order) {
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

    public byte getOrderQuantity() {
        return order.getOrderQuantity();
    }

    public String getOrderStatus() {
        return order.getOrderStatus();
    }

}
