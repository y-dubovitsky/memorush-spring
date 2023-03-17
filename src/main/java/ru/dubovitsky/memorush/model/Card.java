package ru.dubovitsky.memorush.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Table(name = "card_table")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String frontSide;

    private String backSide;

    private boolean isFavorite = false;

    private boolean isLearned = false;

    private String hint; // Подсказка

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private CardSet cardSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return isFavorite == card.isFavorite &&
                isLearned == card.isLearned &&
                Objects.equals(id, card.id) &&
                Objects.equals(frontSide, card.frontSide) &&
                Objects.equals(backSide, card.backSide) &&
                Objects.equals(hint, card.hint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, frontSide, backSide, isFavorite, isLearned, hint);
    }

}
