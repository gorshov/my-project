package service;

import entity.User;
import service.exception.ServiceException;

/**
 * This interface defines special methods for UserService
 */
public interface UserServiceInterface<T> extends BaseServiceInterface<T> {
    /**
     * This method get User by his email
     *
     * @param email user email
     * @return Object of User appropriate
     */
    User getUserByEmail(String email) throws ServiceException;

    /**
     * This method defines actions before deleting Entity from database
     * and then call appropriate dao method
     *
     * @param id Id entity , which should be deleted
     */
    void deleteById(Long id) throws ServiceException;

    /**
     * This method set parametrs for search user by login and passwords
     *
     * @param login
     * @param password
     * @return
     */
    T findUser(String login, String password) throws ServiceException;

    /**
     * Method saves valid data about user and registers him in application
     *
     * @param user
     */
    T registration(User user) throws ServiceException;

    /**
     * Method returns total count of users in database
     *
     * @return
     */
    long getCountUsers() throws ServiceException;

    /**
     * Method returns entity user by name
     *
     * @param name
     * @return
     */
    T getByLastName(String name) throws ServiceException;
}
