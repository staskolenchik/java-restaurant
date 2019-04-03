package filter;

import conn.ConnectionUtils;
import org.apache.log4j.Logger;
import utils.ClassNameUtils;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter{

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public JDBCFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    // Check the target of the request is a servlet?
    private boolean needJDBC(HttpServletRequest request) {

        //
        // Servlet Url-pattern: /spath/*
        //
        // => /spath
        String servletPath = request.getServletPath();
        // => /abc/mnp
        String pathInfo = request.getPathInfo();

        String urlPattern = servletPath;

        if (pathInfo != null) {
            // => /spath/*
            urlPattern = servletPath + "/*";
        }

        // Key: servletName.
        // Value: ServletRegistration
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();

        // Collection of all servlet in your Webapp.
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                log.info(request.getContextPath() + " contains current url = " + urlPattern);
                return true;
            }
        }
        log.info(request.getContextPath() + " doesn't contain current url = " + urlPattern);
        return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        log.info("JDBC filter is enabled");
        HttpServletRequest req = (HttpServletRequest) request;

        // Only open connections for the special requests.
        // (For example, the path to the servlet, JSP, ..)
        //
        // Avoid open connection for commons request.
        // (For example: image, css, javascript,... )
        //
        if (this.needJDBC(req)) {
            log.info("open connection for : " + req.getServletPath());
            Connection conn = null;
            try {
                // Create a Connection.
                conn = ConnectionUtils.getConnection();
                // Set auto commit to false.
                conn.setAutoCommit(false);
                log.info("set connection auto commit = false");

                // Store Connection object in attribute of request.
                MyUtils.storeConnection(request, conn);

                // Allow request to go forward
                // (Go to the next filter or target)
                chain.doFilter(request, response);
                log.info("JDBC filter opened connection to data base for servlets, jsp;" +
                        " invoke next filter or target");
                // Invoke the commit() method to complete the transaction with the DB.
                conn.commit();
                log.info("connection commit, end of transaction");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("data base access error " + e);
                ConnectionUtils.rollbackQuietly(conn);
                throw new ServletException();
            } finally {
                ConnectionUtils.closeQuietly(conn);
            }
        }
        // With commons requests (images, css, html, ..)
        // No need to open the connection.
        else {
            // Allow request to go forward
            // (Go to the next filter or target)
            chain.doFilter(request, response);
            log.info("JDBC filter didn't open connection to data base for images, css, javascript;" +
                    " invoke next filter or target");
        }

    }
}
