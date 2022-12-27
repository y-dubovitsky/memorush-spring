package ru.dubovitsky.flashcardsspring.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(
        name = "category_table",
        indexes = {@Index(name = "idx_category", columnList = "name", unique = true)}
)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_category")
    private Set<CardSet> cardSetsList;

}
