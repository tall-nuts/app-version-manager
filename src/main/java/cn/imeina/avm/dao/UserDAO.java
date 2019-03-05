package cn.imeina.avm.dao;

import cn.imeina.avm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserDAO {

    /**
     * add user
     * @param user
     */
    void save(User user);

    /**
     * query user by uid
     *
     * @param uid userId
     * @return
     */
    User selectById(String uid);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    User update(User user);

    /**
     * 删除用户通过用户ID
     *
     * @param uid
     */
    void delete(String uid);

    /**
     * 修改密码
     * @param uid userId
     * @param password pwd
     */
    void updatePwd(String uid, String password);

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);
}
