package org.example.testareonline.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "optiuni")
public class Optiune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_optiune")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_intrebare", nullable = false)
    private Intrebare intrebare;

    @Column(nullable = false, length = 255)
    private String continut;

    @Column(nullable = false)
    private Integer punctaj;

    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect = false;
}