package service.impl;

import dao.UserDaoInterface;
import entity.User;
import entity.enumiration.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserServiceInterface;
import service.exception.ServiceException;

/**
 * Created by Александр Горшов on 21.05.2017.
 */
@Service
@Transactional
public class UserService extends BaseService<User> implements UserServiceInterface<User> {
    private static Logger log = Logger.getLogger(UserService.class);

    @Autowired
    @Qualifier("userDao")
    private UserDaoInterface daoInterface;

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        log.info("Start getUserByEmail " + email);
        return (User) daoInterface.getUserByEmail(email);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        log.info("Start deleteById " + id);
        daoInterface.deleteById(id);
    }

    @Override
    public User findUser(String login, String password) {
        log.info("Start findUser " + login);
        return (User) daoInterface.findUser(login, password);
    }

    @Override
    public User registration(User user) {
        log.info("Start registration ");
        user.setRole(Role.USER);
        return (User) daoInterface.registration(user);
    }

    @Override
    public long getCountUsers() {
        log.info("Start method getCountUsers");
        return daoInterface.getCountUsers();
    }

    @Override
    public User getByLastName(String name) {
        log.info("Start method getByLastName :" + name);
        return (User) daoInterface.getByLastName(name);
    }
}
