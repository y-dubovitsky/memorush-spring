package ru.dubovitsky.flashcardsspring.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardSetResponseDto {

    private Long id;
    private String name;
    private String description;
    private String tags;
    private Boolean isFavorite;
    private String folder;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdAt;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime updatedAt;
    private Set<CardResponseDto> cardList;

}
