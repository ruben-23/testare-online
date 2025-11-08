package org.example.testareonline.repository;

import org.example.testareonline.entity.Intrebare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntrebareRepository extends JpaRepository<Intrebare, Integer> {
}
