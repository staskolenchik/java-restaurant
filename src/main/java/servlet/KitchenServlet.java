package servlet;


import beans.sql_request_for.Kitchen;
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
import java.util.List;


@WebServlet ("/kitchen")
public class KitchenServlet extends HttpServlet {

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    private static final String KITCHEN_DISH_LIST_VIEW_PAGE = "/WEB-INF/views/kitchDLView.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);
        if(log.isDebugEnabled()) log.debug("get store connection");

        try {
            int orderId = Integer.parseInt(request.getParameter("id"));
            log.info("get from request order id = " + orderId);
            try {
                DBUtils.updateOrderStatusToReady(connection, orderId);
                log.info("Order with id = " + orderId + " was updated to 'ready' status");
            } catch (Exception r) {
            }
        } catch (Exception e) {
            log.info("The first page load");
        }

        List<Kitchen> kitchenDishList = null;
        try {
            kitchenDishList = DBUtils.getFullKitchenDishList(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("kitchenDishList", kitchenDishList);

        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher(KITCHEN_DISH_LIST_VIEW_PAGE);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
