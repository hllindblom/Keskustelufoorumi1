package fi.academy.forumdemo.configurations;

import fi.academy.forumdemo.entities.User;

public interface UserService {
    public User findUserByUsername(String name);
    public void saveUser(User user);
}
