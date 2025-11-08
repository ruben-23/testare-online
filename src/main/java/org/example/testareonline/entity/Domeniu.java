package org.example.testareonline.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "domenii")
public class Domeniu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_domeniu")
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nume;

    @OneToMany(mappedBy = "domeniu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Test> teste;
}