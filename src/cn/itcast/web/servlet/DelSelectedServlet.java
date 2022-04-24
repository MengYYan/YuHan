package cn.itcast.web.servlet; /**
 * @author YuHan
 * @date 2022/4/11
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

@WebServlet(name = "delSelectedServlet", value = "/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] uids = request.getParameterValues("uid");
        UserService service = new UserServiceImpl();
        service.delSelectedUser(uids);

        response.sendRedirect(request.getContextPath() + "/findUserByPageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
