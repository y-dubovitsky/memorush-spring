package ru.dubovitsky.memorush.model;

import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "dictionary_table")
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Unique
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DictionaryItem> dictionaryItems;

}
