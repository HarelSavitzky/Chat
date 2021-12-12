package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The interface User repository.
 */

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find by id user.
     *
     * @param Id the id
     * @return the user
     */
    User findById(long Id);

    /**
     * Find by user name user.
     *
     * @param userName the user name
     * @return the user
     */
    @Query("SELECT n FROM User n WHERE n.userName = ?1")
    User findByUserName(String userName);
}
