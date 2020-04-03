package com.qoajad.backend.service.user;

import com.qoajad.backend.database.DatabaseAccessor;
import com.qoajad.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultUserService")
public class UserServiceImplementation implements UserService {

    private final DatabaseAccessor databaseAccessor;

    @Autowired
    public UserServiceImplementation(@Qualifier("defaultDatabaseAccessor") final DatabaseAccessor databaseAccessor) {
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
    public boolean updateUser(int document, String password) {
        return databaseAccessor.updateUser(document, password);
    }

    @Override
    public boolean deleteUser(int document) {
        return databaseAccessor.deleteUser(document);
    }
}
