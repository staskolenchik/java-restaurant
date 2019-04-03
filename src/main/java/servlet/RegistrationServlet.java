package servlet;

import beans.UserAccount;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/registration"})
public class RegistrationServlet extends HttpServlet {

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public RegistrationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher =
                req.getServletContext().getRequestDispatcher("/WEB-INF/views/registrationView.jsp");
        requestDispatcher.forward(req, resp);
        log.info("forward request to /WEB-INF/views/registrationView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);

        String userName = (String) req.getParameter("userName");
        if (log.isDebugEnabled()) log.debug("get request parameter user name = " + userName);

        String password = (String) req.getParameter("password");
        if (log.isDebugEnabled()) log.debug("get request parameter user password = " + password);

        String phone = (String) req.getParameter("phone");
        if (log.isDebugEnabled()) log.debug("get request parameter user phone = " + phone);

        List<String> roles = new ArrayList<>();
        roles.add("customer");
        if (log.isDebugEnabled()) log.debug("add to user role = " + roles);

        UserAccount userAccount = new UserAccount();
        userAccount.setUserName(userName);
        userAccount.setPassword(password);
        userAccount.setPhone(phone);
        userAccount.setRoles(roles);
        if (log.isDebugEnabled()) log.debug("create user account instance");

        String errorString = null;
        UserAccount existedUserDB = null;

        try {
            existedUserDB = DBUtils.findEqualUser(conn, userName);
        } catch (SQLException e) {
            log.info("There is no equal user account in data base");
        }

        if (userName == null || password == null || phone == null ||
            userName.length() == 0 || password.length() == 0 || phone.length() == 0) {
            errorString = "Please, fill the correct information into fields username, password and phone!";
            log.error("user name, password or phone is null");
        }
        try {
            if (userName.equals(existedUserDB.getUserName())) {
                errorString = userName + " already exists!";
                log.error("user name = " + userName + " already exists");
            }
        } catch (NullPointerException e) {
            log.error("user is not found in data base " + e);
        }

        if (errorString == null) {
            try {
                DBUtils.createUser(conn, userAccount);
            } catch (SQLException e) {
                log.error("couldn't create new user, data base access error " + e);
                errorString = e.getMessage();
            }
        }

        req.setAttribute("errorString", errorString);
        if (log.isDebugEnabled()) log.debug("store attribute for error String = " + errorString);
        req.setAttribute("user", userAccount);
        if (log.isDebugEnabled()) log.debug("store user account attribute");

        //if error return to register page
        if (errorString != null) {
            RequestDispatcher requestDispatcher =
                    req.getServletContext().getRequestDispatcher("/WEB-INF/views/registrationView.jsp");
            requestDispatcher.forward(req, resp);
            log.info("forward request to page with error message /WEB-INF/views/registrationView.jsp");
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/login");
            log.info("redirect to page /WEB-INF/views/registrationView.jsp");
        }

    }
}
