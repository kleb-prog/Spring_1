package Lesson_7.services;


import Lesson_7.entities.SystemUser;
import Lesson_7.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    void save(SystemUser systemUser);
}
