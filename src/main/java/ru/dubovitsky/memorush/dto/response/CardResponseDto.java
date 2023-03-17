package ru.dubovitsky.memorush.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {

    private Long id;
    private String frontSide;
    private String backSide;
    private boolean isFavorite;
    private boolean isLearned;
    private String hint; // Подсказка
    private String category;

}
