package beans.sql_request;

import beans.Dish;
import beans.Order;
import beans.UserAccount;

public class Admin {

    private Dish dish;
    private Order order;
    private UserAccount userAccount;

    public Admin(int orderId, String userAccountName, String dishName,
                 double dishPrice, String orderDate,
                 byte orderQuantity, double orderTotalPrice, String orderStatus) {

        this.userAccount = new UserAccount(userAccountName);
        this.dish = new Dish(dishName, dishPrice);
        this.order = new Order(orderId, orderDate, orderQuantity, orderTotalPrice,orderStatus);
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
