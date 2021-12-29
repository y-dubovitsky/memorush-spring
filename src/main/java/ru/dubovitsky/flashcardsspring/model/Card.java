package ru.dubovitsky.flashcardsspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="flash_card_table")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String frontSide;

    private String backSide;

    private boolean isFavorite;

    private boolean isLearned;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
