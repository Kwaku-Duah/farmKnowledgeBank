package com.management.farm.Repository.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.management.farm.Model.userModels.User;

// this handles the DAO - data access layer (interaction with the database)

@Repository
public interface userRepository extends JpaRepository<User, Long> {
    
}
