package com.qoajad.backend.service.user;

import com.qoajad.backend.database.DatabaseAccessor;
import com.qoajad.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<User>> retrieveAllUsers() {
        return databaseAccessor.retrieveAllUsers();
    }

    @Override
    public ResponseEntity<User> readUser(Integer id) {
        return databaseAccessor.readUser(id);
    }

    @Override
    public ResponseEntity<Void> createUser(Integer id, String password) {
        return databaseAccessor.createUser(id, password);
    }

    @Override
    public ResponseEntity<Void> updateUser(Integer id, String password) {
        return databaseAccessor.updateUser(id, password);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        return databaseAccessor.deleteUser(id);
    }
}
