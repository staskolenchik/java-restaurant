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

@WebServlet(urlPatterns = { "/createDish" })
public class CreateProductServlet extends HttpServlet {

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public CreateProductServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createDishView.jsp");
        dispatcher.forward(request, response);
        log.info("forward request to /WEB-INF/views/createDishView.jsp");
    }

    // When the user enters the product information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String name = (String) request.getParameter("name");
        if (log.isDebugEnabled()) log.debug("get request parameter name = " + name);

        String description = (String) request.getParameter("description");
        if (log.isDebugEnabled()) log.debug("get request parameter description = " + description);

        String dishType = (String) request.getParameter("dishType");
        if (log.isDebugEnabled()) log.debug("get request parameter dish type = " + dishType);

        Dish dish = new Dish();
        dish.setName(name);
        dish.setDescription(description);
        dish.setDishType(dishType);
        if (log.isDebugEnabled()) log.debug("create dish instance");
        String errorString = null;


        if (name == null || description == null || dishType == null ||
                name.length() == 0 || description.length() ==0 || dishType.length() == 0) {
            errorString = "Dish information is incorrect!";
            log.error("Dish information is incorrect, one of dish field  of dish instance may be null");
        }

        if (errorString == null) {
            try {
                DBUtils.insertProduct(conn, dish);
            } catch (SQLException e) {
                log.error("couldn't create a new dish, data base access error " + e);
                errorString = e.getMessage();
            }
        }

        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        if (log.isDebugEnabled()) log.debug("store attribute for error String = " + errorString);
        request.setAttribute("dish", dish);
        if (log.isDebugEnabled()) log.debug("store attribute of dish");

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createDishView.jsp");
            dispatcher.forward(request, response);
            log.info("happened error = " + errorString +
                    ", forward to error page /WEB-INF/views/createDishView.jsp");
        }

        else {
            response.sendRedirect(request.getContextPath() + "/admin");
            if (log.isDebugEnabled()) log.debug("invoke doGet method of path = " +
                    request.getContextPath() + request.getServletPath());
        }
    }

}