package com.gmail.rafaroga46.CamilMovieWatch.repository;

import com.gmail.rafaroga46.CamilMovieWatch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<UserDetails> findUserByEmail(String email);

}
