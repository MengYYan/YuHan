package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author YuHan
 * @date 2022/4/5
 * @apiNote
 */
public interface UserDao {
    /**
     * dao操作语句
     *
     * @return List<User>
     */
    List<User> findAll();

    User findUserByUsernameAndPassword(String username, String password);


    void add(User user);

    void delete(int parseInt);

    User FindById(int parseInt);

    void update(User user);

    /**
     * 查询总记录数
     *
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     */
    List<User> findByPage(int start, int iRows, Map<String, String[]> condition);
}
