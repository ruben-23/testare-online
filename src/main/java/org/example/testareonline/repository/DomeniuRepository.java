package org.example.testareonline.repository;

import org.example.testareonline.entity.Domeniu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomeniuRepository extends JpaRepository<Domeniu, Integer> {
}