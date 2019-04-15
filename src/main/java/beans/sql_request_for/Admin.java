package beans.sql_request_for;

import beans.Dish;
import beans.Order;
import beans.UserAccount;

public class Admin {

    private Dish dish;
    private Order order;
    private UserAccount userAccount;

    public Admin(UserAccount userAccount, Order order, Dish dish) {
        this.userAccount = userAccount;
        this.order = order;
        this.dish = dish;
    }

    public int getOrderId() {
        return order.getOrderId();
    }

    public String getUserAccountName() {
        return userAccount.getUserName();
    }

    public String getDishName() {
        return dish.getName();
    }

    public double getDishPrice() {
        return dish.getDishPrice();
    }

    public String getOrderDate() {
        return order.getOrderDate();
    }

    public byte getOrderQuantity() {
        return order.getOrderQuantity();
    }

    public double getOrderTotalCost() {
        return order.getOrderTotalCost();
    }

    public String getOrderStatus() {
        return order.getOrderStatus();
    }

}
