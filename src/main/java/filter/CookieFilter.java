package filter;

import beans.UserAccount;
import org.apache.log4j.Logger;
import utils.ClassNameUtils;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter{

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public CookieFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("cookie filter is enabled");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        UserAccount userInSession = MyUtils.getLoginedUser(session);

        if (userInSession != null) {
            if (log.isDebugEnabled()) log.debug("user in current session is logined");
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            log.info("set attribute in current session; invoke next filter or target");
            return;
        }

        // Connection was created in JDBCFilter.
        Connection conn = MyUtils.getStoredConnection(request);

        // Flag check cookie
        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null && conn != null) {
            if (log.isDebugEnabled()) log.debug("check if cookie empty and connection to data base succeed");
            String userName = MyUtils.getUserNameInCookie(req);
            if (log.isDebugEnabled()) log.debug("get user name from cookie, user name = " + userName);
            try {
                UserAccount user = DBUtils.findUser(conn, userName);
                MyUtils.storeLoginedUser(session, user);
            } catch (SQLException e) {
                log.error("couldn't find user, data base access error " + e);
            }
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            if (log.isDebugEnabled()) log.debug("checked cookie was marked as checked");
        }

        chain.doFilter(request, response);
        log.info("invoke next filter or target");
    }
}
