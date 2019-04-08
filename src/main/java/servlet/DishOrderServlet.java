package servlet;

import beans.Dish;
import beans.Order;
import beans.UserAccount;
import conn.ConnectionUtils;
import org.apache.log4j.Logger;
import utils.ClassNameUtils;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/dish-order"})
public class DishOrderServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ClassNameUtils.getCurrentClassName());

    private static final String DISH_ORDER_VIEW_PAGE = "/WEB-INF/views/dishOrderView.jsp";
    private static final String DISHES_LIST_VIEW_PAGE = "/dishes";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection connection = MyUtils.getStoredConnection(request);
        log.info("get stored connection from current request");

        int dishId = Integer.parseInt(request.getParameter("id"));
        if (log.isDebugEnabled()) log.debug("get from request dish id parameter = " + dishId);

        Dish dish = null;
        String errorString = null;

        try {
            dish = DBUtils.findProduct(connection, dishId);
            log.info("get dish instance from data base using connection and dish id");
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = "No such dish in db";
            log.error(e.getMessage());
        }

        request.setAttribute("dish", dish);
        if (log.isDebugEnabled()) log.debug("set into request attribute dish instance");
        request.setAttribute("errorString", errorString);
        if (log.isDebugEnabled()) log.debug("set into request attribute errorString = " + errorString);

        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher(DISH_ORDER_VIEW_PAGE);
        if (log.isDebugEnabled()) log.debug("create request dispatcher instance for view page = " +
                DISH_ORDER_VIEW_PAGE);
        dispatcher.forward(request,response);
        log.info("forward request and response to order view page = " + DISH_ORDER_VIEW_PAGE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Connection connection = MyUtils.getStoredConnection(request);
        if (log.isDebugEnabled()) log.debug("get stored connection from request");

        UserAccount loginedUser = MyUtils.getLoginedUser(request.getSession());
        if (log.isDebugEnabled()) log.debug("get UserAccount instance from current session");

        int orderUserId = loginedUser.getId();
        if (log.isDebugEnabled()) log.debug("get user id = " + orderUserId);

        int orderDishId = Integer.parseInt(request.getParameter("id"));
        if (log.isDebugEnabled()) log.debug("get dish id = " + orderDishId);

        byte orderQuantity = Byte.parseByte(request.getParameter("quantity"));
        if (log.isDebugEnabled()) log.debug("get order quantity = " + orderQuantity);

        double orderTotalCost = Double.parseDouble(request.getParameter("totalCost"));
        if (log.isDebugEnabled()) log.debug("get order total cost = " + orderTotalCost);

        String errorString = null;
        Order order = new Order(orderQuantity, orderTotalCost,orderUserId, orderDishId);
        if (log.isDebugEnabled()) log.debug("create order instance");

        Dish dish = null;

        try {
            DBUtils.createOrder(connection, order);
            log.info("add order into data base using order instance and current connection to data base");
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = "data base access error";
            log.error(e.getMessage());
        }

        try {
            dish = DBUtils.findProduct(connection, orderDishId);
            log.info("get dish instance from data base using dish id and current connection to data base");
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = "No such dish in db";
            log.error(e.getMessage());
        }

        if (errorString == null) {
            response.sendRedirect(request.getContextPath() + DISHES_LIST_VIEW_PAGE);
            log.info("redirect to page = " + request.getContextPath() + DISHES_LIST_VIEW_PAGE +
                    " in case there is no errors happened");
        } else {
            request.setAttribute("errorString", errorString);
            if (log.isDebugEnabled()) log.debug("set into request attribute errorString = " + errorString);

            request.setAttribute("dish", dish);
            if (log.isDebugEnabled()) log.debug("set into request attribute dish instance");

            RequestDispatcher dispatcher =
                    request.getServletContext().getRequestDispatcher(DISH_ORDER_VIEW_PAGE);
            if (log.isDebugEnabled()) log.debug("create request dispatcher instance for view page = " +
                    DISH_ORDER_VIEW_PAGE);
            dispatcher.forward(request,response);
            log.info("forward request and response to order view page = " + DISH_ORDER_VIEW_PAGE);
        }
    }
}
