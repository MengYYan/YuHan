package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @author YuHan
 * @date 2022/4/5
 * @apiNote
 */
public class UserServiceImpl implements UserService {
    private final UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用dao完成查询
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User FindUserById(String id) {
        return dao.FindById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] uids) {
        if (uids != null && uids.length > 0) {
            for (String id : uids) {
                dao.delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition) {
        int iCurrentPage = Integer.parseInt(currentPage);
        int iRows = Integer.parseInt(rows);
        if (iCurrentPage <= 0) {
            iCurrentPage = 1;
        }
        //创建一个空pagebean对象
        PageBean<User> pb = new PageBean<>();
        //设置参数
        pb.setCurrentPage(iCurrentPage);
        pb.setRows(iRows);
        //调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        int start = (iCurrentPage - 1) * iRows;
        List<User> list = dao.findByPage(start, iRows, condition);
        pb.setList(list);

        int totalPage = totalCount % iRows == 0 ? totalCount / iRows : totalCount / iRows + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }
}
