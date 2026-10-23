package com.example.userpost.repository;

import com.example.userpost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select distinct u from User u left join fetch u.posts")
    List<User> findAllWithPosts();
    @Query("select u from User u left join fetch u.posts where u.id = :id")
    Optional<User> findByIdWithPosts(Long id);
}
