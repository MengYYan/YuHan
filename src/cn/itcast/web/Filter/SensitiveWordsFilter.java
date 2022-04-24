package cn.itcast.web.Filter; /**
 * @author YuHan
 * @date 2022/4/24
 * @apiNote
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/*
 *敏感词汇过滤器
 * */
@WebFilter(filterName = "SensitiveWordsFilter", urlPatterns = "/*")
public class SensitiveWordsFilter implements Filter {
    //敏感词汇集合
    private List<String> list = new ArrayList<>();

    @Override
    public void init(FilterConfig config) throws ServletException {
        try {
            //1.获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //2.读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        ServletRequest Proxy_req = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.equals("getParameter")) {
                    //增强返回值
                    String value = (String) method.invoke(request, args);
                    if (value != null) {
                        for (String str : list) {
                            if (value.contains(str)) {
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                }
                return method.invoke(request, args);
            }
        });

        chain.doFilter(request, response);
    }
}
