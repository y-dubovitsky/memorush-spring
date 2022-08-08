package ru.dubovitsky.flashcardsspring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String tags;

    private boolean isFavorite = false;

    //FIXME Добавить сущность - ПАПКА
    private String folder;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime updatedAt;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "cardset_id")
    //! Имя cardList - принципиально важно! Нужно создать ResponseDTO
    private Set<Card> cardList;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardSet cardSet = (CardSet) o;
        return Objects.equals(id, cardSet.id) &&
                Objects.equals(name, cardSet.name) &&
                Objects.equals(description, cardSet.description) &&
                Objects.equals(tags, cardSet.tags) &&
                Objects.equals(isFavorite, cardSet.isFavorite) &&
                Objects.equals(folder, cardSet.folder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, tags, isFavorite, folder);
    }
}
