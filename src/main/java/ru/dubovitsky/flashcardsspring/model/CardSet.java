package ru.dubovitsky.flashcardsspring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "cardset_table",
        indexes = {@Index(name = "idx_name", columnList = "name"),
                @Index(name = "idx_user", columnList = "user")}
)
public class CardSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private String description;

    private boolean isFavorite = false;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "fk_cardset")
    //! Имя cardList - принципиально важно! Нужно создать ResponseDTO
    private Set<Card> cardList;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "fk_cardset")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Folder folder;

    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "cardSet_tag",
            joinColumns = @JoinColumn(name = "cardSet_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tagsList;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
