package Lesson_5.Services;

import Lesson_5.SystemUser;
import Lesson_5.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    void save(SystemUser systemUser);
}
