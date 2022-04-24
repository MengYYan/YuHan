package cn.itcast.service;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author YuHan
 * @date 2022/4/5
 * @apiNote
 */
/*
 * 用户管理的业务接口
 */

public interface UserService {
    /**
     * 查询所有用户信息
     *
     * @return List<User>
     */
    List<User> findAll();

    /**
     * @return User
     */
    User login(User user);

    /**
     * 保存user
     */
    void addUser(User user);

    /**
     * 根据id删除
     */
    void deleteUser(String id);

    User FindUserById(String id);

    void updateUser(User user);

    /**
     * 批量删除用户
     */
    void delSelectedUser(String[] uids);


    /**
     * 分页条件查询
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
