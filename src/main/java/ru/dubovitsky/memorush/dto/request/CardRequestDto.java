package ru.dubovitsky.memorush.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
public class CardRequestDto {

    private String frontSide;
    private String backSide;
    private String hint;

}
