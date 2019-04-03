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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public LoginServlet() {
        super();
    }

    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
        dispatcher.forward(request, response);
        log.info("forward request to /WEB-INF/views/homeView.jsp");
    }

    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        if (log.isDebugEnabled()) log.debug("get request parameter user name = " + userName);
        String password = request.getParameter("password");
        if (log.isDebugEnabled()) log.debug("get request parameter user name password = " + password);
        String rememberMeStr = request.getParameter("rememberMe");

        if (log.isDebugEnabled()) log.debug("get request parameter rememberMe = " + rememberMeStr);
        boolean remember = "Y".equals(rememberMeStr);

        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() == 0 ||
                password.length() == 0) {
            hasError = true;
            errorString = "Username, password is incorrect! " +
                    "\nPlease input correct information or use registration!";
            log.error("user name or password is incorrect");
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                user = DBUtils.findUser(conn, userName, password);
                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                log.error("couldn't find user in data base, data base access error " + e);
                hasError = true;
                errorString = e.getMessage();
            }
        }

        if (hasError) {
            user = new UserAccount();
            if (log.isDebugEnabled()) log.debug("create new user instance");
            user.setUserName(userName);
            if (log.isDebugEnabled()) log.debug("set user name = " + userName);
            user.setPassword(password);
            if (log.isDebugEnabled()) log.debug("set user password = " + password);

            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            if (log.isDebugEnabled()) log.debug("store attribute for error String = " + errorString);

            request.setAttribute("user", user);
            if (log.isDebugEnabled()) log.debug("store attribute for user");

            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(request, response);
            log.info("forward request with error message to page /WEB-INF/views/loginView.jsp");
        }

        else {
            HttpSession session = request.getSession();
            if (log.isDebugEnabled()) log.debug("get session from request");
            MyUtils.storeLoginedUser(session, user);

            // If user checked "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
                if (log.isDebugEnabled()) log.debug("store user cookie in current session");
            }
            // Else delete cookie.
            else {
                MyUtils.deleteUserCookie(response);
                if (log.isDebugEnabled()) log.debug("delete user cookie in current session");
            }

            response.sendRedirect(request.getContextPath() + "/userInfo");
            log.info("redirect to " + request.getContextPath() + "/userInfo");
        }
    }

}