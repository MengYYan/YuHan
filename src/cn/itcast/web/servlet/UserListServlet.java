package cn.itcast.web.servlet;
/**
 * @author YuHan
 * @date 2022/4/5
 * @apiNote
 */

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userListServlet", value = "/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用UserService完成查询
        UserService service = new UserServiceImpl();
        List<User> users = service.findAll();
        //将list存入request域中
        request.setAttribute("users", users);
        //转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
