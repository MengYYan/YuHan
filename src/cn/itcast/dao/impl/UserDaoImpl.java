package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author YuHan
 * @date 2022/4/5
 * @apiNote
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(User user) {
        //定义sql
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        //执行sql
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void delete(int id) {
        //定义sql
        String sql = "delete from user where id = ?";
        template.update(sql, id);
    }

    @Override
    public User FindById(int id) {
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public void update(User user) {
        String sql = "update user set name = ?,gender = ?,age = ?,address = ?,qq = ?, email = ? where id = ?";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from user where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keyset = condition.keySet();
        List<Object> params = new ArrayList<>();
        for (String key : keyset) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        sql = sb.toString();
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int iRows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keyset = condition.keySet();
        List<Object> params = new ArrayList<>();
        for (String key : keyset) {
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        sb.append(" limit ?,? ");
        params.add(start);
        params.add(iRows);
        sql = sb.toString();
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), params.toArray());
    }
}
