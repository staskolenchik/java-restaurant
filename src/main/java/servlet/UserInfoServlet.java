package servlet;

import beans.Dish;
import beans.UserAccount;
import beans.sql_request_for.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = { "/userInfo" })
public class UserInfoServlet extends HttpServlet {

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public UserInfoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (log.isDebugEnabled()) log.debug("get session from request");

        UserAccount loginedUser = MyUtils.getLoginedUser(session);
        Connection connection = MyUtils.getStoredConnection(request);
        List<User> orderedDishes = null;
        String errorString = null;
        Double orderTotalCost = null;
        int countAllCurrentOrders = 0;
        int countNotBilledCurrentOrders = 0;

        // Not logged in
        if (loginedUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            log.info("user isn't logined, redirect to " + request.getContextPath() + "/login");
            return;
        }

        try {
            orderedDishes = DBUtils.getFullOrderedDishList(connection, loginedUser);
            log.info("get ordered dish list from data base");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        try {
            orderTotalCost = DBUtils.getOrderedTotalCost(connection, loginedUser);
            log.info("get ordered dish total cost from data base, total cost = " + orderTotalCost);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = "Total cost error";
            log.error(e.getMessage());
        }

        try {
            countAllCurrentOrders = DBUtils.countAllCurrentOrders(connection, loginedUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            countNotBilledCurrentOrders = DBUtils.countNotBilledCurrentOrders(connection, loginedUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean pay = false;
        if (countAllCurrentOrders != 0 && countNotBilledCurrentOrders == 0) {
            pay = true;
        }

        request.setAttribute("pay", pay);

        request.setAttribute("orderTotalCost",orderTotalCost);
        if (log.isDebugEnabled()) log.debug("set into request order total cost = " + orderTotalCost);

        request.setAttribute("errorString",errorString);
        if (log.isDebugEnabled()) log.debug("set into request errorString = " + errorString);

        request.setAttribute("orderedDishList", orderedDishes);
        if (log.isDebugEnabled()) log.debug("set into request ordered dish list");

        request.setAttribute("user", loginedUser);
        if (log.isDebugEnabled()) log.debug("store logined user attribute");

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
        dispatcher.forward(request, response);
        log.info("forward to page /WEB-INF/views/userInfoView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection connection = MyUtils.getStoredConnection(request);
        UserAccount loginedUser = MyUtils.getLoginedUser(request.getSession());

        try {
            DBUtils.updateOrderStatusToArchived(connection, loginedUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/gratitude");

    }

}
