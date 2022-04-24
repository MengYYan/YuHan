package cn.itcast.web.servlet; /**
 * @author YuHan
 * @date 2022/4/6
 * @apiNote
 */

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据 验证码校验
        String verifyCode = request.getParameter("verifycode");
        HttpSession session = request.getSession();
        String checkcodeServer = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
        if (!verifyCode.equalsIgnoreCase(checkcodeServer)) {
            //验证码不正确
            request.setAttribute("login_msg", "验证码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        //4.封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //5.调用Service查询
        UserService service = new UserServiceImpl();
        User loginUser = service.login(user);
        //6.判断是否登录成功
        if (loginUser != null) {
            session.setAttribute("user", loginUser);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            //账号或者密码不正确
            request.setAttribute("login_msg", "用户名或者密码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
