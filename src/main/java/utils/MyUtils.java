package utils;

import beans.UserAccount;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class MyUtils {

    private static int REDIRECT_ID = 0;

    private static  final Map<Integer, String> id_uri_map = new HashMap<>();
    private static final Map<String, Integer> uri_id_map = new HashMap<>();

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    private static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    // Store Connection in request attribute.
    // (Information stored only exist during requests)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
        log.info("store connection to data base in request = " + request.toString());
    }

    // Get the Connection object has been stored in attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        log.info("get connection from request attribute = " + ATT_NAME_CONNECTION);

        return conn;
    }

    // Store user info in Session.
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
        // On the JSP can access via ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
        log.info("store user " +
                " in current session");
    }

    // Get the user information stored in the session.
    public static UserAccount getLoginedUser(HttpSession session) {
        UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
        log.info("get logined user stored in session =" + session.toString());
        return loginedUser;
    }

    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, UserAccount user) {

        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
        log.info("store user's cookie value = " + cookieUserName.getValue() +
                ", for user name = " + user.getUserName());
        cookieUserName.setMaxAge(24 * 60 * 60);
        if (log.isDebugEnabled()) log.debug("set cookie age equals to 1 day");

        response.addCookie(cookieUserName);
        log.info("add cookie in response, cookie value = " + cookieUserName.getValue());
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (log.isDebugEnabled()) log.debug("get array of cookies from request");

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    if (log.isDebugEnabled()) log.debug("get cookie value = " + cookie.getValue() +
                            "from user name = " + ATT_NAME_USER_NAME);

                    return cookie.getValue();
                }
            }
        }
        log.info("there is no such user in cookies with user name = " + ATT_NAME_USER_NAME);
        return null;
    }

    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        if (log.isDebugEnabled()) log.debug("get cookie from user name = " + ATT_NAME_USER_NAME +
                " with value = " + cookieUserName.getValue());

        cookieUserName.setMaxAge(0);
        if(log.isDebugEnabled()) log.debug("set cookie max age = " + cookieUserName.getMaxAge());

        log.info("add cookie in response where cookie max age = " + cookieUserName.getMaxAge());
        response.addCookie(cookieUserName);
    }

    public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
        Integer id = uri_id_map.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;

            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
        }
        return id;
    }

    public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
        String url = id_uri_map.get(redirectId);
        if (url != null) {
            return url;
        }
        return null;
    }
}
