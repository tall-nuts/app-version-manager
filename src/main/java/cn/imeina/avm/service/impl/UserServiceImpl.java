package cn.imeina.avm.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.imeina.avm.dao.UserDAO;
import cn.imeina.avm.exception.BusinessStatus;
import cn.imeina.avm.exception.ServiceException;
import cn.imeina.avm.entity.User;
import cn.imeina.avm.service.IUserService;
import cn.imeina.avm.util.EncryptUtils;
import cn.imeina.avm.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 用户业务处理
 */
@Service("UserService")
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserDAO userDao;

    @Override
    public User login(User user) throws ServiceException {
        if (StrUtil.isEmpty(user.getUserName()) || StrUtil.isEmpty(user.getPassword())){
            throw new ServiceException(BusinessStatus.PARAMETER_ERROR);
        }
        // 如果密码未MD5加密，则本地进行MD5后登录
        String password = user.getPassword();
        if (password.length() < 32){
            String md5Password = EncryptUtils.encryptMD5ToString(password).toLowerCase();
            user.setPassword(md5Password);
            logger.debug("原密码：" + password + ",MD5密码：" + md5Password);
        }
        User loginUser = userDao.login(user);
        if (loginUser != null) {
            String token = JWTUtil.createJWT(user.getUid());
            loginUser.setToken(token);
        } else {
            throw new ServiceException(BusinessStatus.LOGIN_FAILURE);
        }
        return loginUser;
    }

    @Override
    public void logOut() {
        //TODO
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findById(String uid) {
        return userDao.selectById(uid);
    }

    @Override
    public List<User> findAll() {
        return userDao.selectAll();
    }

    @Override
    public void save(User user) throws ServiceException {
        if (StrUtil.isEmpty(user.getUserName()) || StrUtil.isEmpty(user.getPassword())){
            throw new ServiceException(BusinessStatus.PARAMETER_ERROR);
        }
        // 若密码未进行MD5，则本地MD5后注册
        if (user.getPassword().length() < 32){
            String md5Password = EncryptUtils.encryptMD5ToString(user.getPassword()).toLowerCase();
            user.setPassword(md5Password);
        }
        userDao.save(user);
    }

    @Override
    public void delete(String uid) {
        userDao.delete(uid);
    }
}
