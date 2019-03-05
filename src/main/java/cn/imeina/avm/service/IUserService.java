package cn.imeina.avm.service;

import cn.imeina.avm.exception.ServiceException;
import cn.imeina.avm.entity.User;

import java.util.List;

public interface IUserService {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    User login(User user) throws ServiceException;

    /**
     * 退出等
     */
    void logOut();

    /**
     * 更新用户信息
     *
     * @param user
     */
    void update(User user);

    /**
     * 查询用户通过ID
     *
     * @param uid
     * @return
     */
    User findById(String uid);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<User> findAll();

    /**
     * 注册用户
     *
     * @param user
     */
    void save(User user) throws ServiceException;

    /**
     * 删除用户
     *
     * @param uid
     */
    void delete(String uid);
}
