package servlet;

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

@WebServlet(urlPatterns = { "/deleteDish" })
public class DeleteProductServlet extends HttpServlet {

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public DeleteProductServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        int id = Integer.parseInt(request.getParameter("id"));
        if (log.isDebugEnabled()) log.debug("get request parameter id = " + id);

        String errorString = null;

        try {
            DBUtils.deleteProduct(conn, id);
        } catch (SQLException e) {
            log.error("couldn't delete dish, data base access error " + e);
            errorString = e.getMessage();
        }

        // If has an error, redirecte to the error page.
        if (errorString != null) {

            request.setAttribute("errorString", errorString);
            if (log.isDebugEnabled()) log.debug("store attribute for error String = " + errorString);

            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/deleteDishesErrorView.jsp");
            dispatcher.forward(request, response);
            log.info("forward request to error page /WEB-INF/views/deleteDishesErrorView.jsp");
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/admin");
            log.info("redirect to " + request.getContextPath() + "/admin");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        if (log.isDebugEnabled()) log.debug("invoke doGet method of path = " +
                request.getContextPath() + request.getServletPath());
    }

}
