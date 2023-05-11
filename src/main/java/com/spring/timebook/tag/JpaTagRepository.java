package com.spring.timebook.tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTagRepository extends JpaRepository<Tag, Long>, TagRepository {
}
