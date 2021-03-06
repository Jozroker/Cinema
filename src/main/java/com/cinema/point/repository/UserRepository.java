package com.cinema.point.repository;

import com.cinema.point.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findByPhone(String phone);

    @Query("select u from User u where u.username = ?1 or u.email = ?1")
    Optional<User> findByEmailOrUsername(String emailOrUsername);

}
