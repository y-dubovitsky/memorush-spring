package ru.dubovitsky.flashcardsspring.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String frontSide;

    private String backSide;

    private boolean isFavorite;

    private boolean isLearned;

    private String hint; // Подсказка

    @ManyToOne
    private Category category;

}
