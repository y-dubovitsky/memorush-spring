package ru.dubovitsky.memorush.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "dictionaryItem_table")
public class DictionaryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ru;
    private String en;
    private String tr;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Dictionary dictionary;

}
