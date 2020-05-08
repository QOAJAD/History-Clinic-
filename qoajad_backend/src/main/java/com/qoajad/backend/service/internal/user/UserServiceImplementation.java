package com.qoajad.backend.service.internal.user;

import com.qoajad.backend.database.accessor.UserAccessor;
import com.qoajad.backend.model.internal.user.UpdateUser;
import com.qoajad.backend.model.internal.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultUserService")
public class UserServiceImplementation implements UserService {

    private final UserAccessor userAccessor;

    @Autowired
    public UserServiceImplementation(@Qualifier("defaultUserDatabaseAccessor") final UserAccessor userAccessor) {
        this.userAccessor = userAccessor;
    }

    @Override
    public List<User> retrieveAllUsers() {
        return userAccessor.retrieveAllUsers();
    }

    @Override
    public User findUserByDocument(int document) {
        return userAccessor.findUserByDocument(document);
    }

    @Override
    public void createUser(User user) {
        userAccessor.createUser(user);
    }

    @Override
    public boolean updateUser(UpdateUser user) {
        return userAccessor.updateUser(user);
    }

    @Override
    public boolean deleteUser(int document) {
        return userAccessor.deleteUser(document);
    }
}
