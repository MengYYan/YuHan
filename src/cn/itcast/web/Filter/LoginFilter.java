package cn.itcast.web.Filter; /**
 * @author YuHan
 * @date 2022/4/24
 * @apiNote
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 完成登录验证的过滤器
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //1.获取资源请求路径
        HttpServletRequest request1 = (HttpServletRequest) request;
        String uri = request1.getRequestURI();
        if (uri.contains("/login.jsp") || uri.contains("/LoginServlet") || uri.contains("/checkCodeServlet") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/")) {
            chain.doFilter(request, response);
        } else {
            Object user = request1.getSession().getAttribute("user");
            if (user != null) {
                chain.doFilter(request, response);
            } else {
                request1.setAttribute("login_msg", "您还未登录，请登录");
                request1.getRequestDispatcher("/login.jsp").forward(request1, response);
            }
        }
        //chain.doFilter(request, response);
    }
}
