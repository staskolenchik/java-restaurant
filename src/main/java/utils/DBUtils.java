package utils;

import beans.*;
import beans.sql_request.Kitchen;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBUtils {

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public static UserAccount findUser(Connection conn, //
                                       String userName, String password) throws SQLException {

        String sql = "SELECT idusers, username, password, phone, roles FROM cafejavacore.users " //
                + " WHERE username = ? and password= ?";

        if (log.isDebugEnabled()) log.debug("call sql request, sql = " + sql);

        PreparedStatement pstm = conn.prepareStatement(sql);
        if (log.isDebugEnabled()) log.debug("create prepared statement object from sql request, sql = " + sql);

        pstm.setString(1, userName);
        if (log.isDebugEnabled()) log.debug("set first parameter of prepared statement as user name, " +
                "user name = " + userName);

        pstm.setString(2, password);
        if (log.isDebugEnabled()) log.debug("set second parameter of prepared statement as password, " +
                "password = " + password);

        ResultSet rs = pstm.executeQuery();
        if (log.isDebugEnabled())
            log.debug("execute a query of prepared statement for getting the result set of SQL data");

        if (rs.next()) {
            int id = rs.getInt("idusers");
            if (log.isDebugEnabled()) log.debug("getting user id from data base, id = " + id);

            String phone = rs.getString("phone");
            if (log.isDebugEnabled()) log.debug("getting user phone from data base, phone = " + id);

            String[] rolesStrArray = rs.getString("roles").split("[,]");
            List<String> roles = new ArrayList<>(Arrays.asList(rolesStrArray));
            if (log.isDebugEnabled()) log.debug("getting user roles from data base, roles = " + roles);

            UserAccount user = new UserAccount();
            user.setId(id);
            user.setUserName(userName);
            user.setPassword(password);
            user.setPhone(phone);
            user.setRoles(roles);

            log.info("creating user instance, which is existing in data base with name = " + userName + ", " +
                    "and password = " + password);

            return user;
        }
        log.info("there is no user in data base with name = " + userName + ", and password = " + password);
        return null;
    }

    public static UserAccount findUser(Connection conn, String userName) throws SQLException {

        String sql = "SELECT username, password, phone, roles FROM cafejavacore.users " //
                + " WHERE username = ?";
        if (log.isDebugEnabled()) log.debug("call sql request, sql = " + sql);

        PreparedStatement pstm = conn.prepareStatement(sql);
        if (log.isDebugEnabled()) log.debug("create prepared statement object from sql request, sql = " + sql);

        pstm.setString(1, userName);
        if (log.isDebugEnabled()) log.debug("set first parameter of prepared statement as user name, " +
                "user name = " + userName);

        ResultSet rs = pstm.executeQuery();
        if (log.isDebugEnabled())
            log.debug("execute a query of prepared statement for getting the result set of SQL data");

        if (rs.next()) {
            int id = rs.getInt("idusers");
            if (log.isDebugEnabled()) log.debug("get user id from data base, id = " + id);

            String password = rs.getString("password");
            if (log.isDebugEnabled()) log.debug("get user password from data base, password = " + password);

            String phone = rs.getString("phone");
            if (log.isDebugEnabled()) log.debug("get user phone from data base, phone = " + phone);

            List<String> roles = new ArrayList<>();
            String[] rolesStrArray = rs.getString("roles").split("[,]");
            roles.addAll(Arrays.asList(rolesStrArray));
            if (log.isDebugEnabled()) log.debug("getting user roles from data base, roles = " + roles);

            UserAccount user = new UserAccount();
            user.setId(id);
            user.setUserName(userName);
            user.setPassword(password);
            user.setPhone(phone);
            user.setRoles(roles);
            log.info("create user instance, which is existing in data base with user name = " + userName);

            log.info("pass user instance, which is existing in data base with user name = " + userName);
            return user;
        }
        log.info("there is no user in data base with name = " + userName);
        return null;
    }

    public static UserAccount findEqualUser(Connection conn, String userName) throws SQLException {

        String sql = "SELECT username FROM cafejavacore.users " //
                + " WHERE username = ?";
        if (log.isDebugEnabled()) log.debug("call sql request, sql = " + sql);

        PreparedStatement pstm = conn.prepareStatement(sql);
        if (log.isDebugEnabled()) log.debug("create prepared statement object from sql request, sql = " + sql);

        pstm.setString(1, userName);
        if (log.isDebugEnabled()) log.debug("set first parameter of prepared statement as user name, " +
                "user name = " + userName);

        ResultSet rs = pstm.executeQuery();
        if (log.isDebugEnabled())
            log.debug("execute a query of prepared statement for getting the result set of SQL data");

        if (rs.next()) {
            UserAccount user = new UserAccount();
            user.setUserName(userName);

            log.info("create user instance, which is existing in data base with user name = " + userName);
            return user;
        }
        log.info("there is no user in data base with name = " + userName);
        return null;
    }

    public static void createUser(Connection conn, UserAccount userAccount) throws SQLException {

        String sql = "INSERT INTO cafejavacore.users(username, password, phone, roles) VALUES (?,?,?,?)";
        if (log.isDebugEnabled()) log.debug("call sql request, sql = " + sql);

        PreparedStatement pstm = conn.prepareStatement(sql);
        if (log.isDebugEnabled()) log.debug("create prepared statement object from sql request, sql = " + sql);

        pstm.setString(1, userAccount.getUserName());
        if (log.isDebugEnabled()) log.debug("set the first parameter of prepared statement as user name, " +
                "user name = " + userAccount.getUserName());

        pstm.setString(2, userAccount.getPassword());
        if (log.isDebugEnabled()) log.debug("set the second parameter of prepared statement as user password, " +
                "user password = " + userAccount.getPassword());

        pstm.setString(3, userAccount.getPhone());
        if (log.isDebugEnabled()) log.debug("set the third parameter of prepared statement as user phone, " +
                "user phone = " + userAccount.getPhone());

        pstm.setString(4, userAccount.getRoles().get(0));
        if (log.isDebugEnabled()) log.debug("set the forth parameter of prepared statement as user roles, " +
                "user roles = " + userAccount.getRoles().get(0));

        pstm.executeUpdate();
        log.info("new user added in data base");

    }

    public static List<Dish> queryProduct(Connection conn) throws SQLException {
        String sql = "SELECT iddishes, name, description, dishtype, dish_price FROM cafejavacore.dishes";
        if (log.isDebugEnabled()) log.debug("call sql request, sql = " + sql);

        PreparedStatement pstm = conn.prepareStatement(sql);
        if (log.isDebugEnabled()) log.debug("create prepared statement object from sql request, sql = " + sql);

        ResultSet rs = pstm.executeQuery();
        if (log.isDebugEnabled())
            log.debug("execute a query of prepared statement for getting the result set of SQL data");

        List<Dish> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("iddishes");
            if (log.isDebugEnabled()) log.debug("get dish id, id =  " + id);

            String name = rs.getString("name");
            if (log.isDebugEnabled()) log.debug("get dish name, name =  " + name);

            String description = rs.getString("description");
            if (log.isDebugEnabled()) log.debug("get dish description, description =  " + description);

            String dishType = rs.getString("dishtype");
            if (log.isDebugEnabled()) log.debug("get dish type, type =  " + dishType);

            double dishPrice = rs.getDouble("dish_price");
            if (log.isDebugEnabled()) log.debug("get dish price, price = " + dishPrice);

            Dish dish = new Dish();
            dish.setId(id);
            dish.setName(name);
            dish.setDescription(description);
            dish.setDishType(dishType);
            dish.setDishPrice(dishPrice);
            if (log.isDebugEnabled()) log.debug("create a dish instance");

            list.add(dish);
            if (log.isDebugEnabled()) log.debug("add the dish instance into list of dishes");

        }
        log.info("pass a list of dishes");
        return list;
    }

    public static Dish findProduct(Connection conn, int id) throws SQLException {
        String sql = "SELECT iddishes, name, description, dishtype, dish_price " +
                "FROM cafejavacore.dishes WHERE iddishes=?";
        if (log.isDebugEnabled()) log.debug("call sql request, sql = " + sql);

        PreparedStatement pstm = conn.prepareStatement(sql);
        if (log.isDebugEnabled()) log.debug("create prepared statement object from sql request, sql = " + sql);

        pstm.setInt(1, id);
        if (log.isDebugEnabled()) log.debug("set the first parameter of prepared statement as dish id, " +
                "id  = " + id);

        ResultSet rs = pstm.executeQuery();
        if (log.isDebugEnabled())
            log.debug("execute a query of prepared statement for getting the result set of SQL data");

        while (rs.next()) {
            int idDishes = rs.getInt("iddishes");
            if (log.isDebugEnabled()) log.debug("get dish id, id =  " + id);

            String name = rs.getString("name");
            if (log.isDebugEnabled()) log.debug("get dish name, name =  " + name);

            String description = rs.getString("description");
            if (log.isDebugEnabled()) log.debug("get dish description, description =  " + description);

            String dishType = rs.getString("dishtype");
            if (log.isDebugEnabled()) log.debug("get dish type, type =  " + dishType);

            double dishPrice = rs.getDouble("dish_price");
            if (log.isDebugEnabled()) log.debug("get dish price, price =  " + dishPrice);

            Dish dish = new Dish(idDishes, name, description, dishType, dishPrice);
            if (log.isDebugEnabled()) log.debug("create a dish instance");

            log.info("pass the dish, existing in data base with  dish id = " + id);
            return dish;
        }
        log.info("there is no dish in data base with dish id = " + id);
        return null;
    }

    public static void updateProduct(Connection conn, Dish dish) throws SQLException {
        String sql = "UPDATE cafejavacore.dishes SET name =?, description=?, dishtype=?, dish_price=? " +
                "WHERE iddishes=? ";
        if (log.isDebugEnabled()) log.debug("call sql request, sql = " + sql);

        PreparedStatement pstm = conn.prepareStatement(sql);
        if (log.isDebugEnabled()) log.debug("create prepared statement object from sql request, sql = " + sql);

        pstm.setString(1, dish.getName());
        if (log.isDebugEnabled()) log.debug("set the first parameter of prepared statement as dish name, " +
                "name  = " + dish.getName());
        pstm.setString(2, dish.getDescription());
        if (log.isDebugEnabled()) log.debug("set the second parameter of prepared statement as dish description, " +
                "description  = " + dish.getDescription());

        pstm.setString(3, dish.getDishType());
        if (log.isDebugEnabled()) log.debug("set the third parameter of prepared statement as dish type, " +
                "type  = " + dish.getDishType());

        pstm.setDouble(4, dish.getDishPrice());
        if (log.isDebugEnabled()) log.debug("set the third parameter of prepared statement as dish price, " +
                "price  = " + dish.getDishPrice());

        pstm.setInt(5, dish.getId());
        if (log.isDebugEnabled()) log.debug("set the forth parameter of prepared statement as dish id, " +
                "id = " + dish.getId());

        log.info("update dish in data base with dish id = " + dish.getId());
        pstm.executeUpdate();
    }

    public static void insertProduct(Connection conn, Dish dish) throws SQLException {
        String sql = "INSERT INTO cafejavacore.dishes(name, description, dishtype, dish_price) VALUES (?,?,?,?)";
        if (log.isDebugEnabled()) log.debug("call sql request, sql = " + sql);

        PreparedStatement pstm = conn.prepareStatement(sql);
        if (log.isDebugEnabled()) log.debug("create prepared statement object from sql request, sql = " + sql);

        pstm.setString(1, dish.getName());
        if (log.isDebugEnabled()) log.debug("set the first parameter of prepared statement as dish name, " +
                "name  = " + dish.getName());

        pstm.setString(2, dish.getDescription());
        if (log.isDebugEnabled()) log.debug("set the second parameter of prepared statement as dish description, " +
                "description  = " + dish.getDescription());

        pstm.setString(3, dish.getDishType());
        if (log.isDebugEnabled()) log.debug("set the third parameter of prepared statement as dish type, " +
                "type  = " + dish.getDishType());

        pstm.setDouble(4, dish.getDishPrice());
        if (log.isDebugEnabled()) log.debug("set the forth parameter of prepared statement as dish price, " +
                "price  = " + dish.getDishPrice());

        log.info("create dish in data base with dish name = " + dish.getName());
        pstm.executeUpdate();
    }

    public static void deleteProduct(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM cafejavacore.dishes WHERE iddishes= ?";
        if (log.isDebugEnabled()) log.debug("call sql request, sql = " + sql);

        PreparedStatement pstm = conn.prepareStatement(sql);
        if (log.isDebugEnabled()) log.debug("create prepared statement object from sql request, sql = " + sql);

        pstm.setInt(1, id);
        if (log.isDebugEnabled()) log.debug("set the forth parameter of prepared statement as dish id, " +
                "id = " + id);

        log.info("delete dish in data base with dish id = " + id);
        pstm.executeUpdate();
    }

    public static List<Order> getOrderList(Connection connection) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        String SQL = "SELECT id_orders, username,name,quantity_order,date_order,total_price_order " +
                "FROM cafejavacore.orders, cafejavacore.users, cafejavacore.dishes \n" +
                "WHERE users_idusers_fk=idusers AND dishes_iddishes=iddishes";

        PreparedStatement pstm = connection.prepareStatement(SQL);
        if (log.isDebugEnabled()) log.debug("create prepared statement object from sql request, sql = " + SQL);

        ResultSet rs = pstm.executeQuery();
        if (log.isDebugEnabled()) log.debug("create an instance of result set");

        while (rs.next()) {
            int orderId = rs.getInt(1 );
            if (log.isDebugEnabled()) log.debug("get order id = " + orderId);

            String orderUserName = rs.getString(2);
            if (log.isDebugEnabled()) log.debug("get order user name = " + orderUserName);

            String orderDishName = rs.getString(3);
            if (log.isDebugEnabled()) log.debug("get order dish name = " + orderDishName);

            byte orderQuantity = rs.getByte(4);
            if (log.isDebugEnabled()) log.debug("get order quantity = " + orderQuantity);

            java.sql.Timestamp orderDateStr = rs.getTimestamp(5);
            String orderDate = orderDateStr.toString();
            if (log.isDebugEnabled()) log.debug("get order date = " + orderDate);

            double orderTotalCost = rs.getDouble(6);
            if (log.isDebugEnabled()) log.debug("get order total cost = " + orderTotalCost);


            Order order = new Order(orderId,orderUserName,orderDishName,orderQuantity,
                    orderDate,orderTotalCost);
            if (log.isDebugEnabled()) log.debug("create order instance");

            orderList.add(order);
            if (log.isDebugEnabled()) log.debug("add order instance into order list");
        }
        log.info("return filled order list");
        return orderList;
    }

    public static void createOrder(Connection connection, Order order) throws SQLException {
        String SQL = "INSERT INTO cafejavacore.orders(users_idusers_fk, quantity_order, " +
                "total_price_order, dishes_iddishes) \n" +
                "VALUES (?,?,?,?);";

        PreparedStatement prst = connection.prepareStatement(SQL);
        if (log.isDebugEnabled()) log.debug("create prepared statement object from sql request, sql = " + SQL);


        prst.setInt(1, order.getOrderUserId());
        if (log.isDebugEnabled()) log.debug("set 1 parameter of prepared statement " +
                "OrderUserId = " + order.getOrderUserId());


        prst.setInt(2, order.getOrderQuantity());
        if (log.isDebugEnabled()) log.debug("set 2 parameter of prepared statement " +
                "OrderQuantity = " + order.getOrderQuantity());


        prst.setDouble(3, order.getOrderTotalCost());
        if (log.isDebugEnabled()) log.debug("set 3 parameter of prepared statement " +
                "OrderTotalCost = " + order.getOrderTotalCost());


        prst.setInt(4, order.getOrderDishId());
        if (log.isDebugEnabled()) log.debug("set 4 parameter of prepared statement " +
                "OrderDishId = " + order.getOrderDishId());

        prst.executeUpdate();
        log.info("create order in data base with order id = " + order.getOrderId());
    }

    public static List<Dish> getOrderedDishList(Connection connection, UserAccount loginedUser) throws SQLException {
        String SQL = "SELECT name, description,dishtype, dish_price\n" +
                "FROM cafejavacore.dishes, cafejavacore.orders\n" +
                "WHERE users_idusers_fk = ? AND iddishes=dishes_iddishes";

        int userId = loginedUser.getId();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setInt(1, userId);

        List<Dish> orderedDishList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String dishName = resultSet.getString(1);
            String dishDescription = resultSet.getString(2);
            String dishType  = resultSet.getString(3);
            Double dishPrice  = resultSet.getDouble(4);

            Dish dish = new Dish();
            dish.setName(dishName);
            dish.setDescription(dishDescription);
            dish.setDishType(dishType);
            dish.setDishPrice(dishPrice);

            orderedDishList.add(dish);
        }
        return orderedDishList;
    }

    public static double getOrderedTotalCost(Connection connection, UserAccount loginedUser) throws SQLException {
        String SQL = "SELECT SUM(total_price_order)\n" +
                "FROM cafejavacore.orders \n" +
                "WHERE users_idusers_fk = ?";

        PreparedStatement pstm = connection.prepareStatement(SQL);
        int userID = loginedUser.getId();
        pstm.setInt(1, userID);

        ResultSet resultSet = pstm.executeQuery();
        Double orderTotalCost = null;
        while (resultSet.next()) {
            orderTotalCost = resultSet.getDouble(1);
        }
        return orderTotalCost;
    }

    public static List<Dish> getKitchenDishList(Connection connection) throws SQLException {
        String SQL = "SELECT name, description,dishtype\n" +
                "FROM cafejavacore.dishes, cafejavacore.orders\n" +
                "WHERE iddishes=dishes_iddishes";

        List<Dish> kitchenDishList = new ArrayList<>();

        PreparedStatement pstm = connection.prepareStatement(SQL);
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String dishName = resultSet.getString(1);
            String dishDescription = resultSet.getString(2);
            String dishType = resultSet.getString(3);

            Dish dish = new Dish();
            dish.setName(dishName);
            dish.setDescription(dishDescription);
            dish.setDishType(dishType);

            kitchenDishList.add(dish);
        }

        return kitchenDishList;
    }

    public static List<Kitchen> getFullKitchenDishList(Connection connection) throws SQLException {
        String SQL = "SELECT id_orders,name, description,dishtype, quantity_order,status_order\n" +
                "FROM cafejavacore.dishes, cafejavacore.orders\n" +
                "WHERE iddishes=dishes_iddishes and status_order = 'preparing'";

        List<Kitchen> fullKitchenDishList = new ArrayList<>();

        PreparedStatement pstm = connection.prepareStatement(SQL);

        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int orderId = rs.getInt(1);
            String dishName = rs.getString(2);
            String dishDescription = rs.getString(3);
            String dishType = rs.getString(4);
            byte orderQuantity = rs.getByte(5);
            String orderStatus = rs.getString(6);

            Kitchen kitchen = new Kitchen(orderId, dishName, dishDescription, dishType,
                                            orderQuantity,orderStatus);
            fullKitchenDishList.add(kitchen);
        }

        return fullKitchenDishList;
    }

    public static void updateOrderStatusToReady(Connection connection, int orderId) throws SQLException {
        String SQL = "UPDATE cafejavacore.orders SET status_order = 'ready' " +
                    "WHERE (id_orders = ?)";

        PreparedStatement pstm = connection.prepareStatement(SQL);

        pstm.setInt(1,orderId);

        pstm.executeUpdate();
    }
}
