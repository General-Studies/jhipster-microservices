package com.jetherrodrigues.blog.repository;

import com.jetherrodrigues.blog.domain.Blog;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Blog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("select blog from Blog blog where blog.user.login = ?#{principal.preferredUsername}")
    List<Blog> findByUserIsCurrentUser();
}
