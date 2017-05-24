package dao.impl;


import dao.UserDaoInterface;
import entity.User;
import org.apache.log4j.Logger;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;

/**
 * This class contains special methods for working with the entity User
 */
@Repository
public class UserDao extends BaseDao<User> implements UserDaoInterface<User> {
    private static final Logger log = Logger.getLogger(UserDao.class);

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("findUser : " + email);
        String queryUserByEmail = "from User U where U.email=:email";
        Query query = getSession().createQuery(queryUserByEmail).setParameter("email", email);
        return (User) query.uniqueResult();
    }

    @Override
    public User findUser(String login, String password) {
        log.info("findUser : " + login);
        final String queryFindUser = "from User U where U.email=:login and U.passwords=:password";
        Query query = getSession().createQuery(queryFindUser).setParameter("login", login).setParameter("password", password);
        return (User) query.uniqueResult();
    }

    @Override
    public void deleteById(Serializable id) {
        log.info("deleteById : " + id);
        String deleteById = "delete from User U where U.id=:id";
        String deleteReview = "delete from Review R where R.user=:user";
        User user = (User) getSession().get(User.class, id);
        Query queryReview = getSession().createQuery(deleteReview).setParameter("user", user);
        Query queryUser = getSession().createQuery(deleteById).setParameter("id", id);
        queryReview.executeUpdate();
        queryUser.executeUpdate();
    }

    @Override
    public User registration(User user) {
        log.info("registration");
        getSession().save(user);
        return user;
    }

    @Override
    public long getCountUsers() {
        log.info("Start getCountUsers method ");
        String findCount = "select count(U) from User U";
        Query query = getSession().createQuery(findCount);
        return (long) query.uniqueResult();
    }

    @Override
    public User getByLastName(String name) {
        log.info("Start method getByLastName : " + name);
        String queryGetByLastName = "select U from User U where U.lastName=:name";
        Query query = getSession().createQuery(queryGetByLastName).setParameter("name", name);
        User user = (User) query.uniqueResult();
        return user;
    }
}
