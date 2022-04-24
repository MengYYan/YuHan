package cn.itcast.web.servlet; /**
 * @author YuHan
 * @date 2022/4/6
 * @apiNote
 */

import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "delUserServlet", value = "/delUserServlet")
public class DelUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id
        String id = request.getParameter("id");
        //调用service删除
        UserService userService = new UserServiceImpl();
        userService.deleteUser(id);

        //跳转到UserListServlet
        response.sendRedirect(request.getContextPath() + "/findUserByPageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
