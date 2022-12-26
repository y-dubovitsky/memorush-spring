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
@Table(name = "tag_table")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name = "tag_cardSet",
            joinColumns = {@JoinColumn(name = "fk_tag")},
            inverseJoinColumns = {@JoinColumn(name = "fk_cardSet")})
    private Set<CardSet> cardSetsList;

}
