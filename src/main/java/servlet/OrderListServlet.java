package servlet;

import beans.Order;
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

@WebServlet ("/orders")
public class OrderListServlet extends HttpServlet {


    private static final String ORDER_VIEW = "/WEB-INF/views/orderListView.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = MyUtils.getStoredConnection(request);

        String errorString = null;
        List<Order> orderList = null;

        try {
            orderList = DBUtils.getOrderList(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = "order list is empty";
        }

        request.setAttribute("errorString", errorString);

        request.setAttribute("orderList", orderList);

        RequestDispatcher requestDispatcher = request.getServletContext()
                .getRequestDispatcher(ORDER_VIEW);
        requestDispatcher.forward(request,response);

    }


}
