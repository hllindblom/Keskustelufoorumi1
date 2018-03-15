package fi.academy.forumdemo.configurations;

import fi.academy.forumdemo.entities.User;
import fi.academy.forumdemo.entities.UserRole;
import fi.academy.forumdemo.repositories.UserRepository;
import fi.academy.forumdemo.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bcpe;


    @Override
    public User findUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bcpe.encode(user.getPassword()));
//        UserRole userRole = roleRepository.findByRole("ADMIN");
//        user.setRoles(new HashSet<UserRole>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

}
