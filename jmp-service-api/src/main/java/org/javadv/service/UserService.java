package org.javadv.service;

import org.javadv.domain.User;
import org.javadv.impl.exception.UserServiceException;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User updateUser(User user) throws UserServiceException;

    void deleteUser(Long userId);

    User getUser(Long userId) throws UserServiceException;

    List<User> getAllUsers();
}
