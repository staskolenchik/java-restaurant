package servlet;

import beans.sql_request_for.Admin;
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

@WebServlet ("/orders")
public class OrderListServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ClassNameUtils.getCurrentClassName());

    private static final String ORDER_VIEW = "/WEB-INF/views/orderListView.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("id"));
        String orderStatus = request.getParameter("status");

        Connection connection = MyUtils.getStoredConnection(request);
        log.info("get stored connection from request");

        String errorString = null;
        List<Admin> adminList = null;

        switch (orderStatus) {
            case "queueing up" :
                try {
                    DBUtils.updateOrderStatusToPreparing(connection, orderId);
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.error(e.getMessage());
                }
                break;
            case "ready" :
                try {
                    DBUtils.updateOrderStatusToBilled(connection, orderId);
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.error(e.getMessage());
                }
                break;
        }

        try {
            adminList = DBUtils.getFullAdminDishList(connection);
            log.info("get admin from current connection to data base");
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = "order list is empty";
            log.error(e.getMessage());
        }

        request.setAttribute("adminList", adminList);
        if (log.isDebugEnabled()) log.debug("set into request attribute adminList");
        request.setAttribute("errorString", errorString);
        if (log.isDebugEnabled()) log.debug("set into request attribute errorString = " + errorString);

        RequestDispatcher requestDispatcher = request.getServletContext()
                .getRequestDispatcher(ORDER_VIEW);
        if (log.isDebugEnabled()) log.debug("create request dispatcher instance for view page = " +
                                            ORDER_VIEW);
        requestDispatcher.forward(request,response);
        log.info("forward request and response to order view page = " + ORDER_VIEW);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
        if (log.isDebugEnabled()) log.debug("invoke doGet OrderViewList servlet method");
    }


}
