package auth;

import entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import service.UserServiceInterface;
import service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр Горшов on 20.05.2017.
 */
@Component("authService")
public class AuthenticationService implements UserDetailsService {
    private static final Logger log = Logger.getLogger(AuthenticationService.class);

    @Autowired
    private UserServiceInterface userServiceInterface;

    public UserServiceInterface getUserServiceInterface() {
        return userServiceInterface;
    }

    public void setUserServiceInterface(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userServiceInterface.getUserByEmail(email);
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
        }
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString().toUpperCase()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswords(),
                true, true, true, true, authorities);
    }
}
