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


@WebServlet(urlPatterns = { "/editDish" })
public class EditProductServlet extends HttpServlet {

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public EditProductServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(request);

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (log.isDebugEnabled()) log.debug("get request parameter id = " + id);

            Dish dish = null;
            String errorString = null;

            try {
                dish = DBUtils.findProduct(conn, id);
            } catch (SQLException e) {
                log.error("couldn't delete dish, data base access error " + e);
                errorString = e.getMessage();
            }

            if (errorString != null && dish == null) {
                response.sendRedirect(request.getServletPath() + "/admin");
                log.info("the dish does't exist in data base, redirect to path = "
                        + request.getServletPath() + "/admin");
                return;
            }

            request.setAttribute("errorString", errorString);
            if (log.isDebugEnabled()) log.debug("store attribute for error String = " + errorString);
            request.setAttribute("dish", dish);
            if (log.isDebugEnabled()) log.debug("store attribute of dish list");

            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editDishView.jsp");
            dispatcher.forward(request, response);
            log.info("forward request to /WEB-INF/views/editDishView.jsp");

        } catch (Exception e) {
            log.error("id parsing error " + e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(request);

        int id = Integer.parseInt(request.getParameter("id"));
        if (log.isDebugEnabled()) log.debug("get request parameter id = " + id);

        String name = request.getParameter("name");
        if (log.isDebugEnabled()) log.debug("get request parameter name = " + name);

        String description = request.getParameter("description");
        if (log.isDebugEnabled()) log.debug("get request parameter description = " + description);

        String dishType = request.getParameter("dishType");
        if (log.isDebugEnabled()) log.debug("get request parameter dish type = " + dishType);

        Dish dish = new Dish(id, name, description, dishType);
        if (log.isDebugEnabled()) log.debug("create new dish instance");

        String errorString = null;

        try {
            DBUtils.updateProduct(conn, dish);
        } catch (SQLException e) {
            log.error("couldn't update dish, data base access error " + e);
            errorString = e.getMessage();
        }

        request.setAttribute("errorString", errorString);
        if (log.isDebugEnabled()) log.debug("store attribute for error String = " + errorString);
        request.setAttribute("dish", dish);
        if (log.isDebugEnabled()) log.debug("store dish attribute");

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editDishView.jsp");
            dispatcher.forward(request, response);
            log.error("forward with error message to page /WEB-INF/views/editDishView.jsp");
        }

        else {
            response.sendRedirect(request.getContextPath() + "/admin");
            log.info("redirect to page = " + request.getContextPath() + "/admin");
        }
    }

}