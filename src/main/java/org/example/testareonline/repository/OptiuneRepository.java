package org.example.testareonline.repository;

import org.example.testareonline.entity.Optiune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptiuneRepository extends JpaRepository<Optiune, Integer> {
}