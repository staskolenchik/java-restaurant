package beans.sql_request_for;

import beans.Dish;
import beans.Order;

public class Kitchen {

    private Dish dish;
    private Order order;

    public Kitchen(int orderId, String dishName, String dishDescription, String dishType,
                   byte orderQuantity, String orderStatus) {
        this.dish = new Dish();
        this.order = new Order();
        order.setOrderId(orderId);
        dish.setName(dishName);
        dish.setDescription(dishDescription);
        dish.setDishType(dishType);
        order.setOrderQuantity(orderQuantity);
        order.setOrderStatus(orderStatus);
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
