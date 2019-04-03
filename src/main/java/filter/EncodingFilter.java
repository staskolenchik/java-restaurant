package filter;

import org.apache.log4j.Logger;
import utils.ClassNameUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "encodingFilter", urlPatterns = { "/*" })
public class EncodingFilter implements Filter {

    public static final Logger log =
            Logger.getLogger(ClassNameUtils.getCurrentClassName());

    public EncodingFilter() {
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
        log.info("encoding filter is enabled");

        request.setCharacterEncoding("UTF-8");
        if (log.isDebugEnabled()) log.debug("set character encoding to UTF-8");

        chain.doFilter(request, response);
        log.info("invoke next filter or target");
    }
}
