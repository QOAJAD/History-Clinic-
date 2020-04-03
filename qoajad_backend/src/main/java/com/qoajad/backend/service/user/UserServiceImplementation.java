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
    public User findUserById(int id) {
        return databaseAccessor.findUserById(id);
    }

    @Override
    public void createUser(int id, String password) {
        databaseAccessor.createUser(id, password);
    }

    @Override
    public boolean updateUser(int id, String password) {
        return databaseAccessor.updateUser(id, password);
    }

    @Override
    public boolean deleteUser(int id) {
        return databaseAccessor.deleteUser(id);
    }
}
