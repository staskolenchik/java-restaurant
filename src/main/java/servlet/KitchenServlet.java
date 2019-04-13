package servlet;


import beans.sql_request_for.Kitchen;
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

    private static final String KITCHEN_DISH_LIST_VIEW_PAGE = "/WEB-INF/views/kitchDLView.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);


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
        Connection connection = MyUtils.getStoredConnection(request);

        int orderId = Integer.parseInt(request.getParameter("id"));

        try {
            DBUtils.updateOrderStatusToReady(connection, orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Kitchen> fullKitchenList = null;

        try {
            fullKitchenList = DBUtils.getFullKitchenDishList(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("kitchenDishList", fullKitchenList);

        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher(KITCHEN_DISH_LIST_VIEW_PAGE);
        dispatcher.forward(request,response);
    }
}
