package org.example.testareonline.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "intrebari")
public class Intrebare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_intrebare")
    private Integer id;

    @Column(nullable = false, length = 255)
    private String continut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test", nullable = false)
    private Test test;

    @OneToMany(mappedBy = "intrebare", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Optiune> optiuni;
}