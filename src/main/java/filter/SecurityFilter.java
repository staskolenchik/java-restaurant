package filter;


import beans.UserAccount;
import org.apache.log4j.Logger;
import request.UserRoleRequestWrapper;
import utils.ClassNameUtils;
import utils.MyUtils;
import utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        log.info("security filter is enabled");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String servletPath = request.getServletPath();

        //user information stored in session after successful login
        UserAccount loginedUser = MyUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/logout")) {
            chain.doFilter(request, response);
            log.info("path = " + servletPath + "; invoke next filter or target");
            return;
        }

        if (servletPath.equals("/home")) {
            chain.doFilter(request, response);
            log.info("path = " + servletPath + "; invoke next filter or target");
            return;
        }

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            log.info("path = " + servletPath + "; invoke next filter or target");
            return;
        }

        if (servletPath.equals("/registration")) {
            chain.doFilter(request, response);
            log.info("path = " + servletPath + "; invoke next filter or target");
            return;
        }

        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            //username
            String UserName = loginedUser.getUserName();
            List<String> roles = loginedUser.getRoles();

            //wrap old request by a new Request with username and roles information
            wrapRequest = new UserRoleRequestWrapper(UserName, roles, request);
        }

        // pages must be sighed in

        if (SecurityUtils.isSecurePage(request)) {
            //if th user is not logged in.
            //redirect to login page
            if (loginedUser == null) {
                String requestUri = request.getRequestURI();

                //Store the current page to redirect to after successful login
                int redirectId = MyUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }
        }

        //check if the user has a valid role?
        boolean hasPermission = SecurityUtils.hasPermition(wrapRequest);
        if (!hasPermission) {

            RequestDispatcher dispatcher //
                = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");
            dispatcher.forward(request, response);
            return;
        }

        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void destroy() {

    }
}
