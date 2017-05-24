package dao;


import entity.User;

import java.io.Serializable;


/**
 * This interface defines special methods for working with the entity User
 */
public interface UserDaoInterface<T> extends BaseDaoInterface<T> {
    /**
     * This method search user by his email
     *
     * @param email
     * @return
     */
    T getUserByEmail(String email);

    /**
     * This method search user by email and password
     *
     * @param login
     * @param password
     * @return
     */
    T findUser(String login, String password);

    /**
     * This method delete user by id
     *
     * @param id
     */
    void deleteById(Serializable id);

    /**
     * This method allows register a user
     *
     * @param user
     * @return
     */
    T registration(User user);

    /**
     * Method returns total amount of users
     *
     * @return
     */
    long getCountUsers();

    /**
     * This method search users by last name
     *
     * @param name
     * @return
     */
    T getByLastName(String name);
}
