package org.example.testareonline.repository;

import org.example.testareonline.entity.Test;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    @EntityGraph(attributePaths = {"intrebari", "intrebari.optiuni"})
    Optional<Test> findById(Integer id);
}