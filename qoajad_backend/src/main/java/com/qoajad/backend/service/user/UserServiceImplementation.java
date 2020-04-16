package com.qoajad.backend.service.user;

import com.qoajad.backend.database.accessor.UserAccessor;
import com.qoajad.backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultUserService")
public class UserServiceImplementation implements UserService {

    private final UserAccessor databaseAccessor;

    @Autowired
    public UserServiceImplementation(@Qualifier("defaultUserDatabaseAccessor") final UserAccessor databaseAccessor) {
        this.databaseAccessor = databaseAccessor;
    }

    @Override
    public List<User> retrieveAllUsers() {
        return databaseAccessor.retrieveAllUsers();
    }

    @Override
    public User findUserByDocument(int document) {
        return databaseAccessor.findUserByDocument(document);
    }

    @Override
    public void createUser(int document, String password) {
        databaseAccessor.createUser(document, password);
    }

    @Override
    public boolean updateUser(int oldDocument, int newDocument, String password) {
        return databaseAccessor.updateUser(oldDocument, newDocument, password);
    }

    @Override
    public boolean deleteUser(int document) {
        return databaseAccessor.deleteUser(document);
    }
}
