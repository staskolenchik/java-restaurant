package servlet;

import beans.Dish;
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

@WebServlet(urlPatterns = { "/dishes" })
public class CustomerDishListServlet extends HttpServlet {

    private final String CUSTOMER_DISH_LIST_VIEW = "/WEB-INF/views/customerDishListView.jsp";

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public CustomerDishListServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String errorString = null;
        List<Dish> list = null;

        try {
            list = DBUtils.queryProduct(conn);
        } catch (SQLException e) {
            log.error("couldn't show list of dishes, data base access error " + e);
            errorString = e.getMessage();
        }

        request.setAttribute("errorString", errorString);
        if (log.isDebugEnabled()) log.debug("store attribute for error String = " + errorString);
        request.setAttribute("dishList", list);
        if (log.isDebugEnabled()) log.debug("store attribute of dish list");

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher(CUSTOMER_DISH_LIST_VIEW);
        dispatcher.forward(request, response);
        log.info("forward request to " + CUSTOMER_DISH_LIST_VIEW);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        if (log.isDebugEnabled()) log.debug("invoke doGet method of path = " +
                request.getContextPath() + request.getServletPath());
    }

}