package ru.dubovitsky.flashcardsspring.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="flash_card_table")
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

    @ManyToOne
    private CardSet cardSet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
