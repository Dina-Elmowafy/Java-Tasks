package com.example.userpost.repository;

import com.example.userpost.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p left join fetch p.user")
    List<Post> findAllWithUser();
    @Query("select p from Post p left join fetch p.user where p.id = :id")
    Optional<Post> findByIdWithUser(Long id);
    @Query("select p from Post p left join fetch p.user where p.user.id = :userId")
    List<Post> findAllByUserIdWithUser(Long userId);
}
