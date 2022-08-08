package ru.dubovitsky.flashcardsspring.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

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
                Objects.equals(hint, card.hint) &&
                Objects.equals(category, card.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, frontSide, backSide, isFavorite, isLearned, hint, category);
    }

}
