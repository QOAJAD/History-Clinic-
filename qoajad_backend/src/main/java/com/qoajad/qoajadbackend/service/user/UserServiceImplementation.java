package com.qoajad.qoajadbackend.service.user;

import com.qoajad.qoajadbackend.database.DatabaseAccessor;
import com.qoajad.qoajadbackend.model.User;
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
}
