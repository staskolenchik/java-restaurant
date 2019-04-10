package servlet;

import beans.Dish;
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

        List<Dish> kitchenDishList = null;
        try {
            kitchenDishList = DBUtils.getKitchenDishList(connection);
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
        doGet(request,response);
    }
}
